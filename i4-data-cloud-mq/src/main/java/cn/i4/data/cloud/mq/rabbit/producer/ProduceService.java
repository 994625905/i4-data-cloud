package cn.i4.data.cloud.mq.rabbit.producer;

import org.springframework.amqp.core.*;

/**
 * 消息生产者的接口
 * @author wangjc
 * @title: ProduceService
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3015:01
 */
public interface ProduceService {

    /**
     * 创建直连交换机(已存在的话则不重复创建，同下)
     * @param exchangeName
     * @return
     */
    DirectExchange createDirectExchange(String exchangeName, Boolean durable, Boolean autoDelete);

    /**
     * 创建主题交换机
     * @param exchangeName
     * @return
     */
    TopicExchange createTopicExchange(String exchangeName, Boolean durable, Boolean autoDelete);

    /**
     * 创建广播交换机
     * @param exchangeName
     * @return
     */
    FanoutExchange createFanoutExchange(String exchangeName, Boolean durable, Boolean autoDelete);

    /**
     * 创建队列
     * @param queueName
     * @return
     */
    Queue createQueue(String queueName, Boolean durable, Boolean exclusive, Boolean autoDelete);

    /**
     * 将队列绑定到直连交换机上
     * @param directExchange
     * @param queue
     */
    void bindQueueToDirectExchange(DirectExchange directExchange,Queue queue,String routingKey);

    /**
     * 将队列绑定到主题交换机
     * @param topicExchange
     * @param queue
     * @param routingKey
     */
    void bindQueueToTopicExchange(TopicExchange topicExchange,Queue queue,String routingKey);

    /**
     * 将队列绑定到广播交换机
     * @param fanoutExchange
     * @param queue
     * @param routingKey
     */
    void bindQueueToFanoutExchange(FanoutExchange fanoutExchange,Queue queue);

    /**
     * 创建延时队列
     * @param queueName
     * @return
     */
    Queue createDelayQueue(String queueName);

    /**
     * 创建定制化交换机
     * @param exchangeName
     * @return
     */
    CustomExchange createCustomExchange(String exchangeName);

    /**
     * 绑定延时队列到交换机上
     * @param customExchange
     * @param queue
     * @param routingKey
     * @return
     */
    Binding bindDelayQueueToCustomExchange(CustomExchange customExchange,Queue queue,String routingKey);

    /**
     * 发送消息到交换机上，发送普通消息，直连交换机好理解，如果是主题交换机的话，routingKey带有正则表达式的匹配特性
     * @param exchangeName
     * @param routeKey
     * @param msg
     */
    void sendMessage(String exchangeName, String routeKey, String msg);

    /**
     * 发送消息到交换机上，广播交换机，没有路由
     * @param exchangeName
     * @param msg
     */
    void senMessage(String exchangeName,String msg);

    /**
     * 发送延时消息到交换机上
     * @param routingKey
     * @param exchangeName
     * @param msg
     * @param delayTime
     */
    void sendDelayMessage(String routingKey, String exchangeName, String msg, Integer delayTime);
}
