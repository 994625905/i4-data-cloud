package cn.i4.data.cloud.mq.rabbit.config;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.util.SpringBeanUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqExchangeModel;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import cn.i4.data.cloud.mq.rabbit.consume.ConsumerHandler;
import cn.i4.data.cloud.mq.rabbit.producer.ProduceService;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Autowired
    private RedisService redisService;

    @Bean
    public void init(){
        /** 获取交换机配置 */
        String exchangeCache = redisService.get(RedisConstant.KEY.RABBITMQ_EXCHANGE,String.class);
        if(StringUtil.isNullOrEmpty(exchangeCache)){
            logger.error("缓存中交换机配置为null，请手动刷新Redis");
            return;
        }
        List<SetRabbitmqExchangeModel> exchangeList = JSONArray.parseArray(exchangeCache,SetRabbitmqExchangeModel.class);

        /** 获取队列配置 */
        String queueCache = redisService.get(RedisConstant.KEY.RABBITMQ_QUEUE,String.class);
        if(StringUtil.isNullOrEmpty(queueCache)){
            logger.error("缓存中队列配置为null，请手动刷新Redis");
            return;
        }
        List<SetRabbitmqQueueModel> queueList = JSONArray.parseArray(queueCache,SetRabbitmqQueueModel.class);

        /** 逐次匹配并绑定交换机/队列 */
        for(SetRabbitmqExchangeModel exchangeModel:exchangeList){

            /** 直连交换机 */
            if(exchangeModel.getType().intValue() == RabbitMqConstant.EXCHANGE_TYPE.DIRECT){
                DirectExchange directExchange = produceService.createDirectExchange(exchangeModel.getName(), RabbitMqConstant.getDurable(exchangeModel.getDurable()), RabbitMqConstant.getAutoDelete(exchangeModel.getAutoDelete()));
                logger.info("RabbitMQ初始化创建直连交换机完毕：名称[{}]，持久化[{}]，自动删除[{}]",exchangeModel.getName(),RabbitMqConstant.getDurable(exchangeModel.getDurable()), RabbitMqConstant.getAutoDelete(exchangeModel.getAutoDelete()));
                for (SetRabbitmqQueueModel queueModel:queueList){
                    /** 匹配条件，绑定，设置监听 */
                    if(exchangeModel.getId().intValue() == queueModel.getExchangeId().intValue()){
                        Queue queue = produceService.createQueue(queueModel.getName(), RabbitMqConstant.getDurable(queueModel.getDurable()),
                                        RabbitMqConstant.getExclusive(queueModel.getExclusive()), RabbitMqConstant.getAutoDelete(queueModel.getAutoDelete()));
                        produceService.bindQueueToDirectExchange(directExchange,queue,queueModel.getRoutingKey());
                        logger.info("RabbitMQ初始化创建队列完毕，名称[{}]，持久化[{}]，排他性[{}],自动删除[{}]，绑定交换机[{}]",queueModel.getName(),
                                RabbitMqConstant.getDurable(queueModel.getDurable()), RabbitMqConstant.getExclusive(queueModel.getExclusive()), RabbitMqConstant.getAutoDelete(queueModel.getAutoDelete()), exchangeModel.getName());
                        this.addMessageListener(queueModel.getName(),RabbitMqConstant.getIsAck(exchangeModel.getIsAck()));
                    }
                }
            }

            /** 主题交换机 */
            if(exchangeModel.getType().intValue() == RabbitMqConstant.EXCHANGE_TYPE.TOPIC){
                TopicExchange topicExchange = produceService.createTopicExchange(exchangeModel.getName(), RabbitMqConstant.getDurable(exchangeModel.getDurable()), RabbitMqConstant.getAutoDelete(exchangeModel.getAutoDelete()));
                logger.info("RabbitMQ初始化创建主题交换机完毕：名称[{}]，持久化[{}]，自动删除[{}]",exchangeModel.getName(),RabbitMqConstant.getDurable(exchangeModel.getDurable()), RabbitMqConstant.getAutoDelete(exchangeModel.getAutoDelete()));
                for (SetRabbitmqQueueModel queueModel:queueList){
                    /** 匹配条件，绑定，设置监听 */
                    if(exchangeModel.getId().intValue() == queueModel.getExchangeId().intValue()){
                        Queue queue = produceService.createQueue(queueModel.getName(), RabbitMqConstant.getDurable(queueModel.getDurable()),
                                        RabbitMqConstant.getExclusive(queueModel.getExclusive()), RabbitMqConstant.getAutoDelete(queueModel.getAutoDelete()));
                        produceService.bindQueueToTopicExchange(topicExchange,queue,queueModel.getRoutingKey());
                        logger.info("RabbitMQ初始化创建队列完毕，名称[{}]，持久化[{}]，排他性[{}],自动删除[{}]，绑定交换机[{}]",queueModel.getName(),
                                RabbitMqConstant.getDurable(queueModel.getDurable()), RabbitMqConstant.getExclusive(queueModel.getExclusive()), RabbitMqConstant.getAutoDelete(queueModel.getAutoDelete()), exchangeModel.getName());
                        this.addMessageListener(queueModel.getName(),RabbitMqConstant.getIsAck(exchangeModel.getIsAck()));
                    }
                }
            }

            /** 广播交换机 */
            if(exchangeModel.getType().intValue() == RabbitMqConstant.EXCHANGE_TYPE.FANOUT){
                FanoutExchange fanoutExchange = produceService.createFanoutExchange(exchangeModel.getName(), RabbitMqConstant.getDurable(exchangeModel.getDurable()), RabbitMqConstant.getAutoDelete(exchangeModel.getAutoDelete()));
                logger.info("RabbitMQ初始化创建广播交换机完毕：名称[{}]，持久化[{}]，自动删除[{}]",exchangeModel.getName(),RabbitMqConstant.getDurable(exchangeModel.getDurable()), RabbitMqConstant.getAutoDelete(exchangeModel.getAutoDelete()));
                for (SetRabbitmqQueueModel queueModel:queueList){
                    /** 匹配条件，绑定，设置监听 */
                    if(exchangeModel.getId().intValue() == queueModel.getExchangeId().intValue()){
                        Queue queue = produceService.createQueue(queueModel.getName(), RabbitMqConstant.getDurable(queueModel.getDurable()),
                                        RabbitMqConstant.getExclusive(queueModel.getExclusive()), RabbitMqConstant.getAutoDelete(queueModel.getAutoDelete()));
                        produceService.bindQueueToFanoutExchange(fanoutExchange,queue);
                        logger.info("RabbitMQ初始化创建队列完毕，名称[{}]，持久化[{}]，排他性[{}],自动删除[{}]，绑定交换机[{}]",queueModel.getName(),
                                RabbitMqConstant.getDurable(queueModel.getDurable()), RabbitMqConstant.getExclusive(queueModel.getExclusive()), RabbitMqConstant.getAutoDelete(queueModel.getAutoDelete()), exchangeModel.getName());
                        this.addMessageListener(queueModel.getName(),RabbitMqConstant.getIsAck(exchangeModel.getIsAck()));
                    }
                }
            }

            /** 延时交换机 */
            if(exchangeModel.getType().intValue() == RabbitMqConstant.EXCHANGE_TYPE.DELAY){
                CustomExchange customExchange = produceService.createCustomExchange(exchangeModel.getName());
                logger.info("RabbitMQ初始化创建延时交换机完毕：名称[{}]，持久化[{}]，自动删除[{}]",exchangeModel.getName(),RabbitMqConstant.getDurable(exchangeModel.getDurable()), RabbitMqConstant.getAutoDelete(exchangeModel.getAutoDelete()));
                for (SetRabbitmqQueueModel queueModel:queueList){
                    /** 匹配条件，绑定，设置监听 */
                    if(exchangeModel.getId().intValue() == queueModel.getExchangeId().intValue()){
                        Queue delayQueue = produceService.createDelayQueue(queueModel.getName());
                        produceService.bindDelayQueueToCustomExchange(customExchange,delayQueue,queueModel.getRoutingKey());
                        logger.info("RabbitMQ初始化创建队列完毕，名称[{}]，持久化[{}]，排他性[{}],自动删除[{}]，绑定交换机[{}]",queueModel.getName(),
                                RabbitMqConstant.getDurable(queueModel.getDurable()), RabbitMqConstant.getExclusive(queueModel.getExclusive()), RabbitMqConstant.getAutoDelete(queueModel.getAutoDelete()), exchangeModel.getName());
                        this.addMessageListener(queueModel.getName(),RabbitMqConstant.getIsAck(exchangeModel.getIsAck()));
                    }
                }
            }
        }
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
        }
    }
}
