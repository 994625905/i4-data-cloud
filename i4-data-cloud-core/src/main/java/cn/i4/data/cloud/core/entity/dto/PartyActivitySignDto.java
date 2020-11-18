package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.PartyActivitySignModel;
import cn.i4.data.cloud.core.entity.view.PartyActivitySignView;

/**
* Dto
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public class PartyActivitySignDto extends BaseDto<PartyActivitySignView> {

    private static final long serialVersionUID = 1605334833894L;

    /**
     * 活动id
     */
    private Integer activityId;

    private Integer signCount;
    private Integer signTraffic;

    /**
     * 签到
     */
    private PartyActivitySignModel model;
    /**
     * 签到开始时间
     */
    private Integer signStartTime;
    /**
     * 签到结束时间
     */
    private Integer signEndTime;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public PartyActivitySignModel getModel() {
        return model;
    }

    public void setModel(PartyActivitySignModel model) {
        this.model = model;
    }

    public Integer getSignStartTime() {
        return signStartTime;
    }

    public void setSignStartTime(Integer signStartTime) {
        this.signStartTime = signStartTime;
    }

    public Integer getSignEndTime() {
        return signEndTime;
    }

    public void setSignEndTime(Integer signEndTime) {
        this.signEndTime = signEndTime;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public Integer getSignTraffic() {
        return signTraffic;
    }

    public void setSignTraffic(Integer signTraffic) {
        this.signTraffic = signTraffic;
    }
}
