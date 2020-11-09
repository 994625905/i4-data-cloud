package cn.i4.data.cloud.mq.rabbit.config;

/**
 * @author AS065
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/25-16:37
 */
public class RabbitMqConstant {

    /***
     * 交换机类型的依次对应
     */
    public interface EXCHANGE_TYPE{
        int DIRECT = 1;
        int TOPIC = 2;
        int FANOUT = 3;
        int DELAY = 4;
    }

    /**
     * 发送消息用到的交换机名称
     */
    public interface EXCHANGE_NAME{
        String REQUEST = "i4-data-cloud-request";
    }

    /**
     * 发送消息用到的队列
     */
    public interface QUEUE{
        String REQUEST_LOG_ONE = "i4-data-cloud-requestLog-one";//接口请求日志的队列
        String REQUEST_LIMIT_ONE = "i4-data-cloud-requestLimit-one";//接口请求限流的队列
        String REQUEST_PERMISSION_ONE = "i4-data-cloud-requestPermission-one";//接口请求权限拦截的队列
    }

    /**
     * 发送消息用到的路由
     */
    public interface ROUTING_KEY{
        String REQUEST_LOG_ONE = "i4-data-cloud-requestLog-one";//接口请求日志的路由
        String REQUEST_LIMIT_ONE = "i4-data-cloud-requestLimit-one";//接口请求限流的路由
        String REQUEST_PERMISSION_ONE = "i4-data-cloud-requestPermission-one";//接口请求权限拦截的队列
    }



    /**
     * 交换机的持久化！1：true，0：false
     * @param durable
     * @return
     */
    public static Boolean getDurable(int durable){
        return durable == 1;
    }

    /**
     * 队列在完成使用交换机时，是否删除。1：true，0：false
     * @param auto_delete
     * @return
     */
    public static Boolean getAutoDelete(int auto_delete){
        return auto_delete == 1;
    }

    /**
     * 消息监听的消息处理方式，1：true，0：false，默认手动
     * @param is_ack
     * @return
     */
    public static Boolean getIsAck(int is_ack){
        return is_ack == 1;
    }

    /**
     * 消息队列是否只在当前connection生效，1：true，0：false
     * @param exclusive
     * @return
     */
    public static Boolean getExclusive(int exclusive){
        return exclusive == 1;
    }
}
