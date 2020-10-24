package cn.i4.data.cloud.mq.rabbit.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 消息发送到交换机确认机制
 * @author wangjc
 * @title: MsgSendConfirmCallBack
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3011:42
 */
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback{

    private static final Logger logger = LoggerFactory.getLogger(MsgSendConfirmCallBack.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("MsgSendConfirmCallBack[消息发送到交换机成功]");
        if(ack){
            logger.info("消息发送成功");
        }else{
            logger.info("消息发送失败，原因[{}]",cause);
        }
    }
}
