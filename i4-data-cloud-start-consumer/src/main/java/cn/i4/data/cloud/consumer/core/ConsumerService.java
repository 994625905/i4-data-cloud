package cn.i4.data.cloud.consumer.core;

import cn.i4.data.cloud.core.entity.model.LogLimitModel;
import cn.i4.data.cloud.core.entity.model.LogPermissionErrorModel;
import cn.i4.data.cloud.core.entity.model.LogRequestModel;
import cn.i4.data.cloud.core.service.ILogLimitService;
import cn.i4.data.cloud.core.service.ILogPermissionErrorService;
import cn.i4.data.cloud.core.service.ILogRequestService;
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
    private ILogRequestService iLogRequestService;
    @Autowired
    private ILogLimitService iLogLimitService;
    @Autowired
    private ILogPermissionErrorService iLogPermissionErrorService;

    /**
     * 返回值为是否正常处理
     * @param msg
     * @param channel
     * @param queue：根据队列来区分哪一种消息
     * @return
     */
    @Override
    public Boolean handleMessage(String msg, Channel channel, String queue) {

        /** 接口请求日志 */
        if(RabbitMqConstant.QUEUE.REQUEST_LOG_ONE.equals(queue)){
            LogRequestModel model = JSONObject.parseObject(msg,LogRequestModel.class);
            iLogRequestService.save(model);
        }

        /** 接口限流日志 */
        if(RabbitMqConstant.QUEUE.REQUEST_LIMIT_ONE.equals(queue)){
            LogLimitModel model = JSONObject.parseObject(msg, LogLimitModel.class);
            iLogLimitService.save(model);
        }

        /** 接口权限拦截日志 */
        if(RabbitMqConstant.QUEUE.REQUEST_PERMISSION_ONE.equals(queue)){
            LogPermissionErrorModel errorModel = JSONObject.parseObject(msg, LogPermissionErrorModel.class);
            iLogPermissionErrorService.save(errorModel);
        }

        return true;
    }
}
