package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTypeModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTypeView;

/**
* Dto
* @author wangjc
* @date 2020-11-13 13:51:17
*/
public class AfternoonTeaTypeDto extends BaseDto<AfternoonTeaTypeView> {

    private static final long serialVersionUID = 1605246677845L;

    private Integer id;

    private AfternoonTeaTypeModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AfternoonTeaTypeModel getModel() {
        return model;
    }

    public void setModel(AfternoonTeaTypeModel model) {
        this.model = model;
    }
}
