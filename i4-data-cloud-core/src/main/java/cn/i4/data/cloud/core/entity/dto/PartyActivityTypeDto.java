package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityTypeModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityTypeView;

/**
* Dto
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public class PartyActivityTypeDto extends BaseDto<PartyActivityTypeView> {

    private static final long serialVersionUID = 1605334833930L;

    private Integer id;
    private PartyActivityTypeModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PartyActivityTypeModel getModel() {
        return model;
    }

    public void setModel(PartyActivityTypeModel model) {
        this.model = model;
    }
}
