package cn.i4.data.cloud.core.entity.view;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-24 13:57:41
 */
public class SetRabbitmqQueueView extends BaseView<SetRabbitmqQueueView> {

	private static final long serialVersionUID = 1603519061498L;

	public SetRabbitmqQueueView(SetRabbitmqQueueModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.exchangeId = model.getExchangeId();
		this.name = model.getName();
		this.describeInfo = model.getDescribeInfo();
		this.durable = model.getDurable();
		this.autoDelete = model.getAutoDelete();
		this.exclusive = model.getExclusive();
		this.routingKey = model.getRoutingKey();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
	}

	public SetRabbitmqQueueView() {
		
	}
	
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
	@TableField("routing_key")
	private String routingKey;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Long createTime;
	private String createTimeStr;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private Long updateTime;
	private String updateTimeStr;

	/**
	 * 绑定队列
	 */
	private String exchangeName;

	/**
	 * 创建人
	 */
	private String realName;

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

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
}
