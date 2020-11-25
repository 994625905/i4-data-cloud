package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AttendanceDayModel;
import cn.i4.data.cloud.core.entity.view.AttendanceDayView;

/**
* Dto
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public class AttendanceDayDto extends BaseDto<AttendanceDayView> {

    private static final long serialVersionUID = 1606110944655L;

    private Integer id;

    /**
     * 工作日
     */
    private String workDate;
    /**
     * 打卡时间段
     */
    private Integer attendanceStage;
    /**
     * 打卡状态
     */
    private Integer attendanceStatus;
    /**
     * 日期类型
     */
    private Integer workDateType;

    private AttendanceDayModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public Integer getAttendanceStage() {
        return attendanceStage;
    }

    public void setAttendanceStage(Integer attendanceStage) {
        this.attendanceStage = attendanceStage;
    }

    public Integer getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(Integer attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public Integer getWorkDateType() {
        return workDateType;
    }

    public void setWorkDateType(Integer workDateType) {
        this.workDateType = workDateType;
    }

    public AttendanceDayModel getModel() {
        return model;
    }

    public void setModel(AttendanceDayModel model) {
        this.model = model;
    }
}
