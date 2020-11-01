package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.LeaveModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-01 14:58:27
 */
public class LeaveView extends BaseView<LeaveView> {

	private static final long serialVersionUID = 1604213907814L;

	public LeaveView(LeaveModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.title = model.getTitle();
		this.typeId = model.getTypeId();
		this.reason = model.getReason();
		this.startTime = model.getStartTime();
		this.endTime = model.getEndTime();
		this.enclosure = model.getEnclosure();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
	}

	public LeaveView() {
		
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
	 * 请假标题
	 */
	@TableField("title")
	private String title;

	/**
	 * 请假类型
	 */
	@TableField("type_id")
	private Integer typeId;

	/**
	 * 请假原因
	 */
	@TableField("reason")
	private String reason;

	/**
	 * 开始时间
	 */
	@TableField("start_time")
	private Long startTime;

	/**
	 * 结束时间
	 */
	@TableField("end_time")
	private Long endTime;

	/**
	 * 附件的在线地址，多个的话用逗号隔开
	 */
	@TableField("enclosure")
	private String enclosure;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 最后修改时间
	 */
	@TableField("update_time")
	private Long updateTime;

	
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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getStartTime() {
		return this.startTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getEndTime() {
		return this.endTime;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getEnclosure() {
		return this.enclosure;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return this.updateTime;
	}

}
