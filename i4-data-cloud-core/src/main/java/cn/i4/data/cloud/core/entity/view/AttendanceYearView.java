package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AttendanceYearModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-23 13:55:44
 */
public class AttendanceYearView extends BaseView<AttendanceYearView> {

	private static final long serialVersionUID = 1606110944625L;

	public AttendanceYearView(AttendanceYearModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.normalDays = model.getNormalDays();
		this.workDays = model.getWorkDays();
		this.workOverHour = model.getWorkOverHour();
		this.mendCount = model.getMendCount();
		this.lateCount = model.getLateCount();
		this.earlyCount = model.getEarlyCount();
		this.leaveCount = model.getLeaveCount();
		this.leaveHour = model.getLeaveHour();
		this.normalMonth = model.getNormalMonth();
		this.attendanceMonthIds = model.getAttendanceMonthIds();
	}

	public AttendanceYearView() {
		
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
	 * 正常工作日数
	 */
	@TableField("normal_days")
	private Float normalDays;

	/**
	 * 实际工作日数
	 */
	@TableField("work_days")
	private Float workDays;

	/**
	 * 加班时长
	 */
	@TableField("work_over_hour")
	private Float workOverHour;

	/**
	 * 补卡次数
	 */
	@TableField("mend_count")
	private Integer mendCount;

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
	 * 请假小时
	 */
	@TableField("leave_hour")
	private Float leaveHour;

	/**
	 * 正常工作月汇总，逗号隔开
	 */
	@TableField("normal_month")
	private String normalMonth;

	/**
	 * 打卡月汇总，逗号隔开
	 */
	@TableField("attendance_month_ids")
	private String attendanceMonthIds;

	
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

	public void setMendCount(Integer mendCount) {
		this.mendCount = mendCount;
	}

	public Integer getMendCount() {
		return this.mendCount;
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

	public void setNormalMonth(String normalMonth) {
		this.normalMonth = normalMonth;
	}

	public String getNormalMonth() {
		return this.normalMonth;
	}

	public void setAttendanceMonthIds(String attendanceMonthIds) {
		this.attendanceMonthIds = attendanceMonthIds;
	}

	public String getAttendanceMonthIds() {
		return this.attendanceMonthIds;
	}

}
