package cn.i4.data.cloud.system.aspect;

import cn.hutool.core.date.DateUtil;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.JWTUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.core.entity.model.LogRequestModel;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求日志的切面处理
 * @author wangjc
 * @title: RequestLogAspect
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/2019:01
 */
@Aspect
@Component
public class RequestLogAspect extends BaseAspectSupport{

    private static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);
    private static final Integer ACTION_FAIL = 0;//请求异常
    private static final Integer ACTION_SUCCESS = 1;//请求成功
    @Autowired
    private ProduceService produceService;


    /**
     * 此处使用注解扫描
     * 也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(cn.i4.data.cloud.base.annotation.RequestLog)")
    public void logPointCut(){}

    /**
     * 此处使用环绕通知，监听方法执行的耗时
     * @param point
     * @return
     */
    @Around("logPointCut() && @annotation(requestLog)")
    public Object around(ProceedingJoinPoint point, RequestLog requestLog){

        /** 构造发送消息队列的对象 */
        LogRequestModel model = new LogRequestModel();
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setWeek(DateUtil.dayOfWeekEnum(new Date()).toChinese());

        /** 获取IP,用户id */
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes != null){
            HttpServletRequest request = servletRequestAttributes.getRequest();

            model.setRequestIp(this.getIpAddr(request));
            model.setUserId(JWTUtil.getUserId(request,"authorization"));
            model.setLoginName(JWTUtil.getUserName(request,"authorization"));
        }

        /**注解参数*/
        model.setModuleName(requestLog.module());
        model.setActionContent(requestLog.content());
        model.setActionType(requestLog.type().getType());
        model.setActionName(requestLog.type().getName());
        model.setActionMethod(this.resolveMethod(point).getName());

        long start = System.currentTimeMillis();//开始时间，毫秒
        try {
            Object result = point.proceed();//执行切入点
            model.setActionResult(ACTION_SUCCESS);

            return result;
        } catch (Throwable throwable) {

            String exceptionMsg = requestLog.exception();
            if(StringUtil.isNullOrEmpty(exceptionMsg)){
                exceptionMsg = requestLog.content()+"接口异常报错";
            }
            model.setActionException(exceptionMsg);
            model.setActionResult(ACTION_FAIL);

            logger.error(this.resolveMethod(point).getName());
            logger.error(throwable.getMessage());
            throwable.printStackTrace();
            return ActionResult.error("接口异常报错");
        }finally {
            /** 执行时间 */
            model.setActionTime(System.currentTimeMillis() - start);
            /** 发送数据到消息队列，以待入库 */
            produceService.sendMessage(RabbitMqConstant.EXCHANGE_NAME.REQUEST,RabbitMqConstant.ROUTING_KEY.REQUEST_LOG_ONE,JSONObject.toJSONString(model));
        }
    }

}
