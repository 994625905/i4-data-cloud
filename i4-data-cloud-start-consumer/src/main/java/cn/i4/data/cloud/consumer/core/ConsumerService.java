package cn.i4.data.cloud.consumer.core;

import cn.i4.data.cloud.mq.rabbit.consume.ConsumerHandler;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 消费者的service，在i4-data-cloud-mq中自动加载
 * @author wangjc
 * @title: ConsumerService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/2120:06
 */
@Component
public class ConsumerService extends ConsumerHandler {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    /**
     * 返回值为是否正常处理
     * @param msg
     * @param channel
     * @param queue：根据队列来区分哪一种消息
     * @return
     */
    @Override
    public Boolean handleMessage(String msg, Channel channel, String queue) {
        logger.info("msg:[{}]",msg);
        logger.info("channel:[{}]",channel);
        logger.info("queue:[{}]",queue);
        return true;
    }
}
