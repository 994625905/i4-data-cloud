package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AttendanceDayModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-23 13:55:44
 */
public class AttendanceDayView extends BaseView<AttendanceDayView> {

	private static final long serialVersionUID = 1606110944652L;

	public AttendanceDayView(AttendanceDayModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.attendanceId = model.getAttendanceId();
		this.attendanceStage = model.getAttendanceStage();
		this.attendanceStatus = model.getAttendanceStatus();
		this.startTime = model.getStartTime();
		this.endTime = model.getEndTime();
		this.week = model.getWeek();
		this.workDate = model.getWorkDate();
		this.workDateType = model.getWorkDateType();
		this.workHour = model.getWorkHour();
		this.workHourOver = model.getWorkHourOver();
		this.updateStatus = model.getUpdateStatus();
		this.settleStatus = model.getSettleStatus();
	}

	public AttendanceDayView() {
		
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
	 * 打卡id，可手动调整
	 */
	@TableField("attendance_id")
	private Integer attendanceId;

	/**
	 * 0：上班，1：下班
	 */
	@TableField("attendance_stage")
	private Integer attendanceStage;

	/**
	 * -2未打卡，-1迟到，0正常，1早退
	 */
	@TableField("attendance_status")
	private Integer attendanceStatus;

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
	 * 周几
	 */
	@TableField("week")
	private String week;

	/**
	 * 工作日期
	 */
	@TableField("work_date")
	private String workDate;

	/**
	 * 0正常上班，1正常周末，2法定节假日
	 */
	@TableField("work_date_type")
	private Integer workDateType;

	/**
	 * 上班时长，小时
	 */
	@TableField("work_hour")
	private Float workHour;

	/**
	 * 加班时长，小时
	 */
	@TableField("work_hour_over")
	private Float workHourOver;

	/**
	 * 0自动统计，1人为改动
	 */
	@TableField("update_status")
	private Integer updateStatus;

	/**
	 * 核算状态。0无效，1有效（默认）
	 */
	@TableField("settle_status")
	private Integer settleStatus;

	/** 补充字段 */
	private String userName;
	private String attendanceTimeStr;
	private Integer attendMendId;
	private Integer leaveId;
	
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

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Integer getAttendanceId() {
		return this.attendanceId;
	}

	public void setAttendanceStage(Integer attendanceStage) {
		this.attendanceStage = attendanceStage;
	}

	public Integer getAttendanceStage() {
		return this.attendanceStage;
	}

	public void setAttendanceStatus(Integer attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public Integer getAttendanceStatus() {
		return this.attendanceStatus;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getWorkDate() {
		return this.workDate;
	}

	public void setWorkDateType(Integer workDateType) {
		this.workDateType = workDateType;
	}

	public Integer getWorkDateType() {
		return this.workDateType;
	}

	public Float getWorkHour() {
		return workHour;
	}

	public void setWorkHour(Float workHour) {
		this.workHour = workHour;
	}

	public Float getWorkHourOver() {
		return workHourOver;
	}

	public void setWorkHourOver(Float workHourOver) {
		this.workHourOver = workHourOver;
	}

	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}

	public Integer getUpdateStatus() {
		return this.updateStatus;
	}

	public Integer getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(Integer settleStatus) {
		this.settleStatus = settleStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAttendanceTimeStr() {
		return attendanceTimeStr;
	}

	public void setAttendanceTimeStr(String attendanceTimeStr) {
		this.attendanceTimeStr = attendanceTimeStr;
	}

	public Integer getAttendMendId() {
		return attendMendId;
	}

	public void setAttendMendId(Integer attendMendId) {
		this.attendMendId = attendMendId;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
}
