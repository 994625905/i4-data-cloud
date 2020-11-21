package cn.i4.data.cloud.schedule.core;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.service.ILogLimitService;
import cn.i4.data.cloud.core.service.ILogPermissionErrorService;
import cn.i4.data.cloud.core.service.ILogRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 请求日志的业务处理层（请求，限流，权限拦截）
 * @author wangjc
 * @title: RequestLogCore
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/20-18:40
 */
@Component
public class RequestLogCore {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogCore.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private ILogRequestService iLogRequestService;
    @Autowired
    private ILogLimitService iLogLimitService;
    @Autowired
    private ILogPermissionErrorService permissionErrorService;

    /**
     * 同步请求日志
     * @return
     */
    @Async("i4DataCloudScheduleAsyncServiceExecutor")
    public Integer asyncRequestLog(){
        Set<Object> logSet = redisService.setGetMemberOfSetMap(RedisConstant.SET_KEY.REQUEST_LOG);

        /** 清空redis数据 */
        redisService.del(RedisConstant.SET_KEY.REQUEST_LOG);

        /** 数据入库 */
        Integer insert = iLogRequestService.asyncByRedis(logSet);
        logger.info("请求日志数据，从redis同步到MySQL中，共计[{}]条",insert);

        return insert;
    }

    /**
     * 同步请求拦截
     * @return
     */
    @Async("i4DataCloudScheduleAsyncServiceExecutor")
    public Integer asyncRequestLimit(){
        Set<Object> limitSet = redisService.setGetMemberOfSetMap(RedisConstant.SET_KEY.REQUEST_LIMIT);

        /** 清空redis数据 */
        redisService.del(RedisConstant.SET_KEY.REQUEST_LIMIT);

        /** 数据入库 */
        Integer insert = iLogLimitService.asyncByRedis(limitSet);
        logger.info("请求拦截数据，从redis同步到MySQL中，共计[{}]条",insert);

        return insert;
    }

    /**
     * 同步权限拦截数据
     * @return
     */
    @Async("i4DataCloudScheduleAsyncServiceExecutor")
    public Integer asyncRequestPermission(){
        Set<Object> permissionSet = redisService.setGetMemberOfSetMap(RedisConstant.SET_KEY.REQUEST_PERMISSION);

        /** 清空redis数据 */
        redisService.del(RedisConstant.SET_KEY.REQUEST_PERMISSION);

        /** 数据入库 */
        Integer insert = permissionErrorService.asyncByRedis(permissionSet);
        logger.info("权限拦截数据，从redis同步到MySQL中，共计[{}]条",insert);

        return insert;
    }


}
