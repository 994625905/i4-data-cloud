package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaView;

/**
* Dto
* @author wangjc
* @date 2020-11-10 15:14:47
*/
public class AfternoonTeaDto extends BaseDto<AfternoonTeaView> {

    private static final long serialVersionUID = 1604992487118L;

    private Integer id;
    private String name;
    private AfternoonTeaModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AfternoonTeaModel getModel() {
        return model;
    }

    public void setModel(AfternoonTeaModel model) {
        this.model = model;
    }
}
