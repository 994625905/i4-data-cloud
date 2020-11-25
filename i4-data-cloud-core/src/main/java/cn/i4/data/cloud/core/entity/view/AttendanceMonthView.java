package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AttendanceMonthModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-23 13:55:44
 */
public class AttendanceMonthView extends BaseView<AttendanceMonthView> {

	private static final long serialVersionUID = 1606110944612L;

	public AttendanceMonthView(AttendanceMonthModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.year = model.getYear();
		this.month = model.getMonth();
		this.normalDays = model.getNormalDays();
		this.workDays = model.getWorkDays();
		this.workOverHour = model.getWorkOverHour();
		this.lateCount = model.getLateCount();
		this.earlyCount = model.getEarlyCount();
		this.leaveCount = model.getLeaveCount();
		this.leaveHour = model.getLeaveHour();
		this.normalDate = model.getNormalDate();
		this.attendanceDayIds = model.getAttendanceDayIds();
		this.updateStatus = model.getUpdateStatus();
	}

	public AttendanceMonthView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 用户id
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 年
	 */
	@TableField("year")
	private Integer year;

	/**
	 * 月
	 */
	@TableField("month")
	private Integer month;

	/**
	 * 正常工作日数
	 */
	@TableField("normal_days")
	private Float normalDays;

	/**
	 * 工作日数
	 */
	@TableField("work_days")
	private Float workDays;

	/**
	 * 加班时长
	 */
	@TableField("work_over_hour")
	private Float workOverHour;

	/**
	 * 迟到次数
	 */
	@TableField("late_count")
	private Integer lateCount;

	/**
	 * 早退次数
	 */
	@TableField("early_count")
	private Integer earlyCount;

	/**
	 * 请假次数
	 */
	@TableField("leave_count")
	private Integer leaveCount;

	/**
	 * 累计请假小时
	 */
	@TableField("leave_hour")
	private Float leaveHour;

	/**
	 * 正常工作日汇总，逗号隔开
	 */
	@TableField("normal_date")
	private String normalDate;

	/**
	 * 打卡日汇总，逗号隔开
	 */
	@TableField("attendance_day_ids")
	private String attendanceDayIds;

	/**
	 * 0自动统计，1人为改动
	 */
	@TableField("update_status")
	private Integer updateStatus;

	/** 补充字段 */
	private String userName;
	
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setNormalDays(Float normalDays) {
		this.normalDays = normalDays;
	}

	public Float getNormalDays() {
		return this.normalDays;
	}

	public void setWorkDays(Float workDays) {
		this.workDays = workDays;
	}

	public Float getWorkDays() {
		return this.workDays;
	}

	public void setWorkOverHour(Float workOverHour) {
		this.workOverHour = workOverHour;
	}

	public Float getWorkOverHour() {
		return this.workOverHour;
	}

	public void setLateCount(Integer lateCount) {
		this.lateCount = lateCount;
	}

	public Integer getLateCount() {
		return this.lateCount;
	}

	public void setEarlyCount(Integer earlyCount) {
		this.earlyCount = earlyCount;
	}

	public Integer getEarlyCount() {
		return this.earlyCount;
	}

	public void setLeaveCount(Integer leaveCount) {
		this.leaveCount = leaveCount;
	}

	public Integer getLeaveCount() {
		return this.leaveCount;
	}

	public void setLeaveHour(Float leaveHour) {
		this.leaveHour = leaveHour;
	}

	public Float getLeaveHour() {
		return this.leaveHour;
	}

	public void setNormalDate(String normalDate) {
		this.normalDate = normalDate;
	}

	public String getNormalDate() {
		return this.normalDate;
	}

	public void setAttendanceDayIds(String attendanceDayIds) {
		this.attendanceDayIds = attendanceDayIds;
	}

	public String getAttendanceDayIds() {
		return this.attendanceDayIds;
	}

	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}

	public Integer getUpdateStatus() {
		return this.updateStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
