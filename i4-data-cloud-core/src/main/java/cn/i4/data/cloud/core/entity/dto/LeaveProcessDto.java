package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public class LeaveProcessDto extends BaseDto<LeaveProcessView> {

    private static final long serialVersionUID = 1604213907791L;

    /**
     * 申请model
     */
    private LeaveProcessModel processModel;
    /**
     * 抄送列表
     */
    private List<Integer> ccUserList;

    public LeaveProcessModel getProcessModel() {
        return processModel;
    }

    public void setProcessModel(LeaveProcessModel processModel) {
        this.processModel = processModel;
    }

    public List<Integer> getCcUserList() {
        return ccUserList;
    }

    public void setCcUserList(List<Integer> ccUserList) {
        this.ccUserList = ccUserList;
    }
}
