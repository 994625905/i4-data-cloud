package cn.i4.data.cloud.mq.rabbit.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 消息发送到交换机返回机制
 * @author wangjc
 * @title: MsgSendReturnCallBack
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3011:45
 */
public class MsgSendReturnCallBack implements RabbitTemplate.ReturnCallback {

    private static final Logger logger = LoggerFactory.getLogger(MsgSendReturnCallBack.class);

    /**
     * 当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
     * 需要注意的是：该方法调用后，{@link MsgSendConfirmCallBack}中的confirm方法也会被调用，且ack = true
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("MsgSendReturnCallBack[消息从交换机到队列失败]:[{}]",message.toString());
        logger.info("replyCode:[{}]",replyCode);
        logger.info("replyText:[{}]",replyText);
        logger.info("exchange:[{}]",exchange);
        logger.info("routingKey:[{}]",routingKey);
    }
}
