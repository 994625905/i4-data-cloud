package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-10 19:54:04
 */
public class AfternoonTeaMenuView extends BaseView<AfternoonTeaMenuView> {

	private static final long serialVersionUID = 1605009244112L;

	public AfternoonTeaMenuView(AfternoonTeaMenuModel model) {
		this.id = model.getId();
		this.afternoonTeaIds = model.getAfternoonTeaIds();
		this.date = model.getDate();
		this.week = model.getWeek();
		this.endTime = model.getEndTime();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
		this.createUserId = model.getCreateUserId();
		this.status = model.getStatus();
	}

	public AfternoonTeaMenuView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 下午茶id的列表字符串，逗号隔开
	 */
	@TableField("afternoon_tea_ids")
	private String afternoonTeaIds;

	/**
	 * 日期
	 */
	@TableField("date")
	private String date;

	/**
	 * 周几
	 */
	@TableField("week")
	private String week;

	/**
	 * 结束时间，过期无法点单
	 */
	@TableField("end_time")
	private Long endTime;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 
	 */
	@TableField("update_time")
	private Long updateTime;

	/**
	 * 
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 0未发布，1已发布，2已过期
	 */
	@TableField("status")
	private Integer status;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setAfternoonTeaIds(String afternoonTeaIds) {
		this.afternoonTeaIds = afternoonTeaIds;
	}

	public String getAfternoonTeaIds() {
		return this.afternoonTeaIds;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getWeek() {
		return this.week;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getEndTime() {
		return this.endTime;
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

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
