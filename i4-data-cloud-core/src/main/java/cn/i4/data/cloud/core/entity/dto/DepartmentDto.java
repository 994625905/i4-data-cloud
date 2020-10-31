package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.DepartmentModel;
import cn.i4.data.cloud.core.entity.view.DepartmentView;

/**
* Dto
* @author wangjc
* @date 2020-10-31 15:24:09
*/
public class DepartmentDto extends BaseDto<DepartmentView> {

    private static final long serialVersionUID = 1604129049748L;

    private Integer id;

    private DepartmentModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartmentModel getModel() {
        return model;
    }

    public void setModel(DepartmentModel model) {
        this.model = model;
    }
}
