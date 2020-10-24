package cn.i4.data.cloud.core.entity.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.i4.data.cloud.base.entity.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* Model
* @author wangjc
* @date 2020-10-24 13:57:41
*/
@TableName("t_set_rabbitmq_queue")
public class SetRabbitmqQueueModel extends BaseModel<SetRabbitmqQueueModel> {

    private static final long serialVersionUID = 1603519061493L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 绑定的交换机id
    */
    @TableField("exchange_id")
    private Integer exchangeId;

    /**
    * 
    */
    @TableField("name")
    private String name;

    /**
    * 描述信息
    */
    @TableField("describe_info")
    private String describeInfo;

    /**
    * 队列的持久化！1true，0false
    */
    @TableField("durable")
    private Integer durable;

    /**
    * 消息队列没有在使用时将被自动删除，1true，0false
    */
    @TableField("auto_delete")
    private Integer autoDelete;

    /**
    * 消息队列是否只在当前connection生效，1true，0false
    */
    @TableField("exclusive")
    private Integer exclusive;

    /**
    * 路由key，绑定队列到交换机的
    */
    @TableField("routingKey")
    private String routingkey;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setUserId(Integer userId) {
    this.userId = userId;
    }

    public Integer getUserId() {
    return this.userId;
    }

    public void setExchangeId(Integer exchangeId) {
    this.exchangeId = exchangeId;
    }

    public Integer getExchangeId() {
    return this.exchangeId;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getName() {
    return this.name;
    }

    public void setDescribeInfo(String describeInfo) {
    this.describeInfo = describeInfo;
    }

    public String getDescribeInfo() {
    return this.describeInfo;
    }

    public void setDurable(Integer durable) {
    this.durable = durable;
    }

    public Integer getDurable() {
    return this.durable;
    }

    public void setAutoDelete(Integer autoDelete) {
    this.autoDelete = autoDelete;
    }

    public Integer getAutoDelete() {
    return this.autoDelete;
    }

    public void setExclusive(Integer exclusive) {
    this.exclusive = exclusive;
    }

    public Integer getExclusive() {
    return this.exclusive;
    }

    public void setRoutingkey(String routingkey) {
    this.routingkey = routingkey;
    }

    public String getRoutingkey() {
    return this.routingkey;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

}
