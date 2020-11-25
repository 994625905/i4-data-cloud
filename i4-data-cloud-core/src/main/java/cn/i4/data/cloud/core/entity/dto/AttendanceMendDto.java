package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendModel;
import cn.i4.data.cloud.core.entity.model.AttendanceMendProcessModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendView;

/**
* Dto
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public class AttendanceMendDto extends BaseDto<AttendanceMendView> {

    private static final long serialVersionUID = 1606110944680L;

    private Integer id;

    /**
     * 打卡时间段
     */
    private Integer attendanceStage;

    private AttendanceMendModel model;

    private AttendanceMendProcessModel processModel;

    /**
     * 流程表的id
     */
    private Integer processId;

    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttendanceStage() {
        return attendanceStage;
    }

    public void setAttendanceStage(Integer attendanceStage) {
        this.attendanceStage = attendanceStage;
    }

    public AttendanceMendModel getModel() {
        return model;
    }

    public void setModel(AttendanceMendModel model) {
        this.model = model;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AttendanceMendProcessModel getProcessModel() {
        return processModel;
    }

    public void setProcessModel(AttendanceMendProcessModel processModel) {
        this.processModel = processModel;
    }
}
