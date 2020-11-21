package cn.i4.data.cloud.consumer.core;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.mq.rabbit.config.RabbitMqConstant;
import cn.i4.data.cloud.mq.rabbit.consume.ConsumerHandler;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消费者的service，在i4-data-cloud-mq中自动加载，部署多个consumer就是负载均衡
 * @author wangjc
 * @title: ConsumerService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/2120:06
 */
@Component
public class ConsumerService extends ConsumerHandler {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private RedisService redisService;

    /**
     * 返回值为是否正常处理。
     * 第一次优化：为了避免MySQL频繁的写入，就先set到redis中，然后定时任务来同步到MySQL
     * @param msg
     * @param channel
     * @param queue：根据队列来区分哪一种消息
     * @return
     */
    @Override
    public Boolean handleMessage(String msg, Channel channel, String queue) {

        /** 接口请求日志 */
        if(RabbitMqConstant.QUEUE.REQUEST_LOG_ONE.equals(queue)){
            redisService.setAddSetMap(RedisConstant.SET_KEY.REQUEST_LOG,msg);
        }

        /** 接口限流日志 */
        if(RabbitMqConstant.QUEUE.REQUEST_LIMIT_ONE.equals(queue)){
            redisService.setAddSetMap(RedisConstant.SET_KEY.REQUEST_LIMIT,msg);
        }

        /** 接口权限拦截日志 */
        if(RabbitMqConstant.QUEUE.REQUEST_PERMISSION_ONE.equals(queue)){
            redisService.setAddSetMap(RedisConstant.SET_KEY.REQUEST_PERMISSION,msg);
        }

        return true;
    }
}
