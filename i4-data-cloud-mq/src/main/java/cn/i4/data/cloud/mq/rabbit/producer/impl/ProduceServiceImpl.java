package cn.i4.data.cloud.mq.rabbit.producer.impl;

import cn.i4.data.cloud.mq.rabbit.producer.ProduceService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息生产者的实现类
 * @author wangjc
 * @title: ProduceServiceImpl
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3015:02
 */
@Service
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * direct交换器相对来说比较简单，匹配规则为：如果路由键匹配，消息就被投送到相关的队列
     * @param exchangeName
     * @return
     */
    @Override
    public DirectExchange createDirectExchange(String exchangeName) {
        /**
         * 1.durable=true ，交换机的持久化！rabbitmq重启的时候不需要创建新的交换机，
         */
        DirectExchange directExchange = new DirectExchange(exchangeName,true,false);
        this.rabbitAdmin.declareExchange(directExchange);
        return directExchange;
    }

    /**
     * topic交换机采用模糊匹配路由键的原则进行转发消息到队列中，发布订阅者模式，
     * @param exchangeName
     * @return
     */
    @Override
    public TopicExchange createTopicExchange(String exchangeName) {
        TopicExchange topicExchange = new TopicExchange(exchangeName,true,false);
        this.rabbitAdmin.declareExchange(topicExchange);
        return topicExchange;
    }

    @Override
    public Queue createQueue(String queueName) {
        /**
         * exclusive：排他性，独有
         */
        Queue queue = new Queue(queueName, true, false, false);
        this.rabbitAdmin.declareQueue(queue);
        return queue;
    }

    /**
     * withQueueName：按照队列名称为路由键
     * @param directExchange
     * @param queue
     */
    @Override
    public void bindQueueToDirectExchange(DirectExchange directExchange, Queue queue) {
        Binding binding = BindingBuilder.bind(queue).to(directExchange).withQueueName();
        this.rabbitAdmin.declareBinding(binding);
    }

    @Override
    public void bindQueueToTopicExchange(TopicExchange topicExchange, Queue queue,String routingKey) {
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
        this.rabbitAdmin.declareBinding(binding);
    }

    /**
     * 创建专属的延时队列（exclusive）
     * @param queueName
     * @return
     */
    @Override
    public Queue createDelayQueue(String queueName) {
        Queue queue = new Queue(queueName, true);
        this.rabbitAdmin.declareQueue(queue);
        return queue;
    }

    /**
     * 创建专属的交换机，为了处理延时
     * @param exchangeName
     * @return
     */
    @Override
    public CustomExchange createCustomExchange(String exchangeName) {
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        CustomExchange customExchange = new CustomExchange(exchangeName, "x-delayed-message", true, false, args);
        this.rabbitAdmin.declareExchange(customExchange);
        return customExchange;
    }

    @Override
    public Binding bindDelayQueueToCustomExchange(CustomExchange customExchange, Queue queue, String name) {
        Binding bind = BindingBuilder.bind(queue).to(customExchange).with(name).noargs();
        this.rabbitAdmin.declareBinding(bind);
        return bind;
    }

    /**
     * 发送普通消息
     * @param exchangeName
     * @param routeKey：上述的交换机与队列绑定默认是按照队列名称绑定的
     * @param msg
     */
    @Override
    public void sendMessage(String exchangeName,String routeKey, String msg) {
        this.rabbitTemplate.send(exchangeName,routeKey,new Message(msg.getBytes(),new MessageProperties()));
    }

    /**
     * 发送延时消息
     * @param routingKey
     * @param exchangeName
     * @param msg
     * @param delayTime
     */
    @Override
    public void sendDelayMessage(String routingKey, String exchangeName, String msg, Integer delayTime) {
        this.rabbitTemplate.setExchange(exchangeName);
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 设置延时
                message.getMessageProperties().setHeader("x-delay",delayTime);
                return message;
            }
        };
        this.rabbitTemplate.convertAndSend(routingKey,exchangeName,msg,messagePostProcessor);
    }
}
