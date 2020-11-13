package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public class AfternoonTeaTaskDto extends BaseDto<AfternoonTeaTaskView> {

    private static final long serialVersionUID = 1605009244099L;

    private Integer id;

    private AfternoonTeaTaskModel model;

    private List<AfternoonTeaMenuModel> menuList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AfternoonTeaTaskModel getModel() {
        return model;
    }

    public void setModel(AfternoonTeaTaskModel model) {
        this.model = model;
    }

    public List<AfternoonTeaMenuModel> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<AfternoonTeaMenuModel> menuList) {
        this.menuList = menuList;
    }
}
