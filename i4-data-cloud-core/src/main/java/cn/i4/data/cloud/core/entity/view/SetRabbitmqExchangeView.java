package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqExchangeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-24 13:57:40
 */
public class SetRabbitmqExchangeView extends BaseView<SetRabbitmqExchangeView> {

	private static final long serialVersionUID = 1603519061348L;

	public SetRabbitmqExchangeView(SetRabbitmqExchangeModel model) {
		this.id = model.getId();
		this.createUserId = model.getCreateUserId();
		this.name = model.getName();
		this.describeInfo = model.getDescribeInfo();
		this.durable = model.getDurable();
		this.autoDelete = model.getAutoDelete();
		this.createTime = model.getCreateTime();
	}

	public SetRabbitmqExchangeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 创建人
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 描述名称
	 */
	@TableField("describe_info")
	private String describeInfo;

	/**
	 * 交换机的持久化！1true，0false
	 */
	@TableField("durable")
	private Integer durable;

	/**
	 * 当所有队列在完成使用此exchange时，是否删除。1true，0false
	 */
	@TableField("auto_delete")
	private Integer autoDelete;

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

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
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

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

}
