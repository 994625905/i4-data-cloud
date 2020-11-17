package cn.i4.data.cloud.system.aspect;

import cn.hutool.core.date.DateUtil;
import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.JWTUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.entity.model.LogLimitModel;
import cn.i4.data.cloud.mq.rabbit.config.RabbitMqConstant;
import cn.i4.data.cloud.mq.rabbit.producer.ProduceService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求接口限流的切面处理
 * @author wangjc
 * @title: RequestLimitAspect
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/28-19:23
 */
@Aspect
@Component
@Order(2)
public class RequestLimitAspect extends BaseAspectSupport{

    private static final Logger logger = LoggerFactory.getLogger(RequestLimitAspect.class);
    private static final int IP_CODE = 428;//IP限制的状态码
    private static final int USER_CODE = 429;//用户限制的状态码
    private static final int NORMAL_CODE = 430;//当前接口限制的状态码
    @Autowired
    private ProduceService produceService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 此处使用注解扫描
     * 也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(cn.i4.data.cloud.base.annotation.RequestLimit)")
    public void logPointCut(){}

    /**
     * 此处使用环绕通知，监听方法执行的耗时
     * @param point
     * @return
     */
    @Around("logPointCut() && @annotation(requestLimit)")
    public Object around(ProceedingJoinPoint point, RequestLimit requestLimit){
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String ip_addr = this.getIpAddr(request);
            Method method = this.resolveMethod(point);

            /** 设置key */
            String key = requestLimit.key();
            if(requestLimit.type().getCode() == IP_CODE){
                key += "_"+ip_addr;
            }
            if(requestLimit.type().getCode() == USER_CODE){
                key += "_"+JWTUtil.getUserName(request,"authorization");
            }
            if(requestLimit.type().getCode() == NORMAL_CODE){
                key += "_"+method.getName();
            }

            /** 构造不可变的安全list */
            ImmutableList<String> keyList = ImmutableList.of(StringUtils.join(requestLimit.prefix() + "_", key));
            /** 构造Redis脚本 */
            RedisScript<Long> script = new DefaultRedisScript<>(this.buildLuaScript(),Long.class);
            /** 执行脚本 */
            Long count = redisTemplate.execute(script, keyList, requestLimit.count(), requestLimit.period());

            /** 结果判断，超出范围则拒绝 */
            if(count != null && count.intValue() <= requestLimit.count()){
                return point.proceed();
            }else{

                /** 构造发送消息队列的对象 */
                LogLimitModel limitModel = new LogLimitModel();
                limitModel.setCount(requestLimit.count());
                limitModel.setPeriod(requestLimit.period());
                limitModel.setName(requestLimit.name());
                limitModel.setLimitKey(key);
                limitModel.setPrefix(requestLimit.prefix());
                limitModel.setCreateTime(System.currentTimeMillis()/1000L);

                /** 获取请求路径，IP/用户 */
                limitModel.setRequestPath(request.getServletPath());//请求路径

                if(requestLimit.type().getCode() == IP_CODE){//IP限制，一般用于登录，注册，获取验证码…………之类的数据
                    limitModel.setType(0);
                    limitModel.setTypeContent(ip_addr);
                }
                if(requestLimit.type().getCode() == USER_CODE){//USER限制，一般是对功能接口操作次数的限制
                    limitModel.setType(1);
                    limitModel.setTypeContent(JWTUtil.getUserName(request,"authorization"));
                }
                if(requestLimit.type().getCode() == NORMAL_CODE){//当前接口限制的状态码，针对当前接口做全局限制
                    limitModel.setType(2);
                    limitModel.setTypeContent(method.getName());
                }
                /** 发送数据到消息队列，以待入库 */
                produceService.sendMessage(RabbitMqConstant.EXCHANGE_NAME.REQUEST,RabbitMqConstant.ROUTING_KEY.REQUEST_LIMIT_ONE,JSONObject.toJSONString(limitModel));

                return ActionResult.error("当前操作已触发限流保护机制，请控制访问频率");
            }
        }catch (Throwable e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error("限流操作异常，请稍后再试");
        }
    }

    /**
     * 限流脚本
     * 调用的时候不超过阈值，则直接返回并执行计算器自加。
     * @return lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }

}
