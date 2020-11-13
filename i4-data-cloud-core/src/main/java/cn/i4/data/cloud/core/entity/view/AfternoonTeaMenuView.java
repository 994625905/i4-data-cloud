package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-11 11:41:50
 */
public class AfternoonTeaMenuView extends BaseView<AfternoonTeaMenuView> {

	private static final long serialVersionUID = 1605066110251L;

	public AfternoonTeaMenuView(AfternoonTeaMenuModel model) {
		this.id = model.getId();
		this.teaTaskId = model.getTeaTaskId();
		this.teaIds = model.getTeaIds();
		this.date = model.getDate();
		this.week = model.getWeek();
		this.endTime = model.getEndTime();
		this.createTime = model.getCreateTime();
		this.createUserId = model.getCreateUserId();
	}

	public AfternoonTeaMenuView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 下午茶任务id
	 */
	@TableField("tea_task_id")
	private Integer teaTaskId;

	/**
	 * 下午茶id的列表字符串，逗号隔开
	 */
	@TableField("tea_ids")
	private String teaIds;

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
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * teaIds对应的下午茶列表
	 */
	private List<AfternoonTeaView> teaList;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTeaTaskId(Integer teaTaskId) {
		this.teaTaskId = teaTaskId;
	}

	public Integer getTeaTaskId() {
		return this.teaTaskId;
	}

	public void setTeaIds(String teaIds) {
		this.teaIds = teaIds;
	}

	public String getTeaIds() {
		return this.teaIds;
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

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public List<AfternoonTeaView> getTeaList() {
		return teaList;
	}

	public void setTeaList(List<AfternoonTeaView> teaList) {
		this.teaList = teaList;
	}
}
