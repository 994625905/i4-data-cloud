package cn.i4.data.cloud.system.aspect;

import cn.hutool.core.date.DateUtil;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.JWTUtil;
import cn.i4.data.cloud.base.util.StringUtil;
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
    @Value("${i4.data.cloud.mq.rabbit.producersExchange}")
    private String systemExchange;
    @Value("${i4.data.cloud.mq.rabbit.requestLogQueue}")
    private String requestLogQueue;


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

        /** 构造发送消息队列的map */
        Map<String,Object> map = new HashMap<String, Object>(){{
            put("createTime",System.currentTimeMillis()/1000L);
            put("week", DateUtil.dayOfWeekEnum(new Date()).toChinese());
            put("userId", JWTUtil.getUserId());
            put("loginname",JWTUtil.getUserName());
        }};

        /** 获取IP */
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes != null){
            map.put("ip",servletRequestAttributes.getRequest().getRemoteAddr());
        }

        /**注解参数*/
        map.put("moduleName",requestLog.module());
        map.put("actionContent",requestLog.content());
        map.put("actionType",requestLog.type().getType());
        map.put("actionName",requestLog.type().getName());
        map.put("actionMethod",this.resolveMethod(point).getName());

        try {
            long start = System.currentTimeMillis();//开始时间，毫秒
            Object result = point.proceed();//执行切入点
            long end = System.currentTimeMillis();//结束时间，毫秒

            /** 执行时间，成功 */
            map.put("actionTime",end - start);
            map.put("action_success",ACTION_SUCCESS);

            return result;
        } catch (Throwable throwable) {

            String exceptionMsg = requestLog.exception();
            if(StringUtil.isNullOrEmpty(exceptionMsg)){
                exceptionMsg = requestLog.content()+"接口异常报错";
            }
            map.put("actionException",exceptionMsg);
            map.put("action_success",ACTION_FAIL);

            logger.error(this.resolveMethod(point).getName());
            logger.error(throwable.getMessage());
            throwable.printStackTrace();
            return ActionResult.error();
        }finally {
            /** 发送数据到消息队列，以待入库 */
            produceService.sendMessage(this.systemExchange,this.requestLogQueue,JSONObject.toJSONString(map));
        }
    }

}
