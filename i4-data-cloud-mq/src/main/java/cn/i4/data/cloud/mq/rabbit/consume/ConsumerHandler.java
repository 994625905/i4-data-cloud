package cn.i4.data.cloud.mq.rabbit.consume;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjc
 * @title: ConsumerHander
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3014:03
 */
public abstract class ConsumerHandler implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerHandler.class);

    /**
     * 存储消费队列的map
     */
    private ConcurrentHashMap<String, AcknowledgeMode> queueAckMap = new ConcurrentHashMap<>();

    /**
     * 将需要消费的队列和签收模式存入map，不允许重写
     * @param queue
     * @param acknowledgeMode
     */
    public final void setQueueAckMap(String queue,AcknowledgeMode acknowledgeMode){
        this.queueAckMap.put(queue,acknowledgeMode);
        logger.info("注入队列[{}],消息签收模式：[{}]",queue,acknowledgeMode.name());
    }

    /**
     * 抽象方法，给子类重写的执行业务
     * @param msg
     * @param channel
     * @param queue
     * @return
     */
    public abstract Boolean handleMessage(String msg, Channel channel, String queue);

    /**
     * 接收消息
     * @param message
     * @param channel
     */
    public void onMessage(Message message,Channel channel){
        logger.info("===============消费者接收到RabbitMQ消息========================");
        try {
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            String queue = message.getMessageProperties().getConsumerQueue();
            String msg = new String(message.getBody());
            logger.debug("队列：[{}]，内容：[{}]",queue,msg);

            // 具体业务执行处理
            Boolean handleResult = this.handleMessage(msg, channel, queue);
            // 确认消息
            this.ackMessage(msg,channel,queue,deliveryTag,handleResult);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 确认消息
     * @param msg
     * @param channel
     * @param queue
     * @param deliveryTag:RabbitMQ 向该 Channel 投递这条消息的唯一标识 ID，单调递增的正整数
     * @param handleResult
     */
    private void ackMessage(String msg,Channel channel,String queue,Long deliveryTag,Boolean handleResult){
        try {
            if(handleResult){
                AcknowledgeMode acknowledgeMode = this.queueAckMap.get(queue);
                if(acknowledgeMode != null && acknowledgeMode.isManual()){
                    // 手动签收 todo
                    channel.basicAck(deliveryTag,false);
                }else{
                    logger.debug("=================已消费,自动签收=========================");
                }
            }else{
                logger.debug("=========================消费异常，业务可能出错============================");
            }
        }catch (Exception e){
            logger.error("=========================确认消息异常============================");
            e.printStackTrace();
        }
    }

}
