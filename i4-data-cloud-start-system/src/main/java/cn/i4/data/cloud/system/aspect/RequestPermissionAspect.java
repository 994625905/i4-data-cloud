package cn.i4.data.cloud.system.aspect;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.CookieUtil;
import cn.i4.data.cloud.base.util.JWTUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.entity.model.LogPermissionErrorModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;
import cn.i4.data.cloud.mq.rabbit.config.RabbitMqConstant;
import cn.i4.data.cloud.mq.rabbit.producer.ProduceService;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求权限的切面处理
 * @author wangjc
 * @title: RequestPermission
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/9-9:20
 */
@Aspect
@Component
public class RequestPermissionAspect extends BaseAspectSupport {

    private final static Logger logger = LoggerFactory.getLogger(RequestPermissionAspect.class);
    @Autowired
    protected RedisService redisService;
    @Autowired
    private ProduceService produceService;

    /**
     * 此处使用注解扫描
     * 也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(cn.i4.data.cloud.base.annotation.RequestPermission)")
    public void logPointCut(){}

    /**
     * 此处使用环绕通知，做拦截处理，拦截ModelAndView和ActionResult，
     * 所以authorization并不一定在请求头中，要从cookie中获取更为准确
     * @param point
     * @return
     */
    @Around("logPointCut() && @annotation(requestPermission)")
    public Object around(ProceedingJoinPoint point, RequestPermission requestPermission){
        /** 获取请求方法的类型，ModelAndView/ActionResult */
        Method method = this.resolveMethod(point);
        String typeName = method.getReturnType().getSimpleName();

        try {
            String permission = requestPermission.value();

            /** 获取当前用户的所有权限 */
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String authorization = CookieUtil.getCookieValue(request,"authorization");
            List<MenuButtonView> menuList = redisService.get(RedisConstant.KEY.LOGIN_USER_ROLE_MENU_TREE_PREFIX + JWTUtil.getUserId(authorization), List.class);

            /** 权限验证，或者没有被标注权限标识符的接口也放行 */
            if(checkPermission(menuList,permission) || StringUtil.isNullOrEmpty(permission)){
                return point.proceed();
            }

            /** 权限验证失败，做错误处理 */
            LogPermissionErrorModel errorModel = new LogPermissionErrorModel();
            errorModel.setClassName(method.getDeclaringClass().getName());
            errorModel.setMethodName(method.getName());
            errorModel.setRequestPath(request.getServletPath());
            errorModel.setPermission(permission);
            errorModel.setUserId(JWTUtil.getUserId(authorization));
            errorModel.setLoginName(JWTUtil.getUserName(authorization));
            errorModel.setCreateTime(System.currentTimeMillis()/1000L);

            if("ModelAndView".equals(typeName)){
                logger.debug("页面权限不够:class[{}]，method[{}]",method.getDeclaringClass().getName(),method.getName());

                /** 发送到队列，以待入库 */
                errorModel.setType(0);
                produceService.sendMessage(RabbitMqConstant.EXCHANGE_NAME.REQUEST,RabbitMqConstant.ROUTING_KEY.REQUEST_PERMISSION_ONE, JSONObject.toJSONString(errorModel));

                return new ModelAndView("/error/408");
            }else{
                logger.debug("接口权限不够:class[{}]，method[{}]",method.getDeclaringClass().getName(),method.getName());

                /** 发送到队列，以待入库 */
                errorModel.setType(1);
                produceService.sendMessage(RabbitMqConstant.EXCHANGE_NAME.REQUEST,RabbitMqConstant.ROUTING_KEY.REQUEST_PERMISSION_ONE, JSONObject.toJSONString(errorModel));

                return ActionResult.error("权限验证拦截");
            }
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage());
            throwable.printStackTrace();
            if("ModelAndView".equals(typeName)){
                return new ModelAndView("/error/409");
            }else{
                return ActionResult.error("权限验证错误");
            }
        }
    }

    /**
     * 验证权限，递归验证
     * @param list
     * @param permission
     * @return
     */
    private Boolean checkPermission(List<MenuButtonView> list,String permission){
        Boolean flag = false;
        if(list != null && list.size() > 1){
            for(MenuButtonView menuButton:list){
                if(permission.equals(menuButton.getPermission())){
                    flag = true;
                    break;
                }else{
                    if(this.checkPermission(menuButton.getChild(),permission)){
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}







