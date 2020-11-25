package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AttendanceMendModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-23 13:55:44
 */
public class AttendanceMendView extends BaseView<AttendanceMendView> {

	private static final long serialVersionUID = 1606110944677L;

	public AttendanceMendView(AttendanceMendModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.createTime = model.getCreateTime();
		this.attendanceDayId = model.getAttendanceDayId();
		this.reason = model.getReason();
	}

	public AttendanceMendView() {
		
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
	 * 创建时间
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 打卡日id
	 */
	@TableField("attendance_day_id")
	private Integer attendanceDayId;

	/**
	 * 补卡原因
	 */
	@TableField("reason")
	private String reason;

	/** 补充字段 */
	private String createTimeStr;
	private String userName;
	private String processId;
	private String processStatus;
	/** 补卡日期，时间段 */
	private String attendanceWorkDate;
	private Integer attendanceStage;
	/** 流程的相关字段和下一节点的代办人 */
	private String processDefId;
	private String deploymentId;
	private String processInstanceId;
	private String receiveUserId;

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

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setAttendanceDayId(Integer attendanceDayId) {
		this.attendanceDayId = attendanceDayId;
	}

	public Integer getAttendanceDayId() {
		return this.attendanceDayId;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return this.reason;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getAttendanceWorkDate() {
		return attendanceWorkDate;
	}

	public void setAttendanceWorkDate(String attendanceWorkDate) {
		this.attendanceWorkDate = attendanceWorkDate;
	}

	public Integer getAttendanceStage() {
		return attendanceStage;
	}

	public void setAttendanceStage(Integer attendanceStage) {
		this.attendanceStage = attendanceStage;
	}

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
}
