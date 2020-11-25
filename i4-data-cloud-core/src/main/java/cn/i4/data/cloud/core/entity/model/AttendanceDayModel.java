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
* @date 2020-11-23 13:55:44
*/
@TableName("t_attendance_day")
public class AttendanceDayModel extends BaseModel<AttendanceDayModel> {

    private static final long serialVersionUID = 1606110944649L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
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
    * 工作日期
    */
    @TableField("work_date")
    private String workDate;

    /**
    * 0正常上班，1法定节假日
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
}
