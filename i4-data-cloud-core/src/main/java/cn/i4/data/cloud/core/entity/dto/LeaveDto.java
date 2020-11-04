package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.LeaveModel;
import cn.i4.data.cloud.core.entity.model.LeaveProcessModel;
import cn.i4.data.cloud.core.entity.view.LeaveView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public class LeaveDto extends BaseDto<LeaveView> {

    private static final long serialVersionUID = 1604213907817L;

    private Integer id;

    private Integer processId;

    private Integer typeId;

    private String title;

    private LeaveModel model;

    private String modelTag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LeaveModel getModel() {
        return model;
    }

    public void setModel(LeaveModel model) {
        this.model = model;
    }

}
