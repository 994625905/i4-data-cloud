package cn.i4.data.cloud.mq.rabbit.config;

import cn.i4.data.cloud.base.util.SpringBeanUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.mq.rabbit.consume.ConsumerHandler;
import cn.i4.data.cloud.mq.rabbit.producer.ProduceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 初始化配置，生成交换机和队列，bean的优先级设置最低
 * @author wangjc
 * @title: InitConfig
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3013:52
 */
@Component
@Order(99)
public class InitConfig {

    private static final Logger logger = LoggerFactory.getLogger(InitConfig.class);

    @Autowired
    private ProduceService produceService;
    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 生产者，消费者，延时时长：（多个队列就逗号隔开）
     * 确认机制
     */
    @Value("${i4.data.cloud.mq.rabbit.producersExchange}")
    private String producersExchange;
    @Value("${i4.data.cloud.mq.rabbit.producersQueue}")
    private String producersQueue;
    @Value("${i4.data.cloud.mq.rabbit.consumersExchange}")
    private String consumersExchange;
    @Value("${i4.data.cloud.mq.rabbit.consumersQueue}")
    private String consumersQueue;
    @Value("${i4.data.cloud.mq.rabbit.delaysExchange}")
    private String delaysExchange;
    @Value("${i4.data.cloud.mq.rabbit.delaysQueue}")
    private String delaysQueue;
    @Value("${i4.data.cloud.mq.rabbit.isAck}")
    private String isAckStr;

    @Bean
    public void init(){
        Boolean isAck = false;//默认手动
        if(!StringUtil.isNullOrEmpty(this.isAckStr) && isAckStr.equals("true")){
            isAck = true;
        }
        // 生产端初始化交换机
        if(!StringUtil.isNullOrEmpty(this.producersExchange)){
            DirectExchange directExchange = produceService.createDirectExchange(this.producersExchange);

            // 初始化队列
            if(!StringUtil.isNullOrEmpty(this.producersQueue)){
                for(String queueName:this.producersQueue.split(",")){
                    Queue queue = produceService.createQueue(queueName);
                    produceService.bindQueueToDirectExchange(directExchange,queue);
                    this.addMessageListener(queueName,isAck);
                }
            }
        }
        // 消费端初始化交换机
        if(!StringUtil.isNullOrEmpty(this.consumersExchange)){
            DirectExchange directExchange = produceService.createDirectExchange(this.consumersExchange);

            // 初始化队列
            if(!StringUtil.isNullOrEmpty(this.consumersQueue)){
                for(String queueName:this.consumersQueue.split(",")){
                    Queue queue = produceService.createQueue(queueName);
                    produceService.bindQueueToDirectExchange(directExchange,queue);
                    this.addMessageListener(queueName,isAck);
                }
            }
        }
        // 延时初始化交换机
        if(!StringUtil.isNullOrEmpty(this.delaysExchange)){
            CustomExchange customExchange = produceService.createCustomExchange(this.delaysExchange);

            if(!StringUtil.isNullOrEmpty(this.delaysQueue)){
                for(String queueName:this.delaysQueue.split(",")){
                    Queue delayQueue = produceService.createDelayQueue(queueName);
                    produceService.bindDelayQueueToCustomExchange(customExchange, delayQueue, queueName);
                    addMessageListener(queueName, isAck);
                }
            }
        }
        logger.info("RabbitMQ初始化交换机完毕：producersExchange[{}],consumersExchange[{}],delaysExchange[{}]",this.producersExchange,this.consumersExchange,this.delaysExchange);
        logger.info("RabbitMQ初始化队列完毕：producersQueue[{}],consumersQueue[{}],delaysQueue[{}]",this.producersQueue,this.consumersQueue,this.delaysQueue);
    }

    /**
     * 添加消息监听器（手动cache异常）
     * @param queue
     * @param isAck
     */
    private void addMessageListener(String queue,Boolean isAck){
        try {
            ConsumerHandler consumerHandler = SpringBeanUtil.getBean(ConsumerHandler.class);
            if(consumerHandler != null){
                SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
                container.setConnectionFactory(this.connectionFactory);
                container.setQueueNames(queue);
                if(isAck){
                    consumerHandler.setQueueAckMap(queue, AcknowledgeMode.NONE);//
                }
                container.setAcknowledgeMode(AcknowledgeMode.NONE);//无ack模式，默认都会被确认签收，不断的推送，效率高（但有丢失风险）
                MessageListenerAdapter adapter = new MessageListenerAdapter(consumerHandler);
                container.setMessageListener(adapter);
                container.start();
                logger.info("已成功开始监听RabbitMQ异步消息：queue[{}] ",queue);
            }else{
                logger.info("======================= 消息监听器缺少对应的处理类 ======================");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            logger.error("该应用下缺乏对应的消息监听器");
            e.printStackTrace();
        }
    }
}
