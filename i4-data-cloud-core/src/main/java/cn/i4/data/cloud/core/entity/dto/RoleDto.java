package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.RoleModel;
import cn.i4.data.cloud.core.entity.view.RoleView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public class RoleDto extends BaseDto<RoleView> {

    private static final long serialVersionUID = 1602394945483L;

    private Integer id;

    private String name;

    private RoleModel model;

    private List<Integer> menuIdList;

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

    public RoleModel getModel() {
        return model;
    }

    public void setModel(RoleModel model) {
        this.model = model;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
