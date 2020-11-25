package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.AttendanceView;

/**
* Dto
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public class AttendanceDto extends BaseDto<AttendanceView> {

    private static final long serialVersionUID = 1606110944602L;

    /**
     * 工作日
     */
    private String workDate;
    /**
     * 工作时间段
     */
    private Integer attendanceStage;

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
}
