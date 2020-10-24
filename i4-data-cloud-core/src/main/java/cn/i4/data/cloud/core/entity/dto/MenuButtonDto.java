package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.MenuButtonModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;

/**
* Dto
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public class MenuButtonDto extends BaseDto<MenuButtonView> {

    private static final long serialVersionUID = 1602394945639L;

    private Integer pid;
    private Integer id;
    private Integer status;
    private MenuButtonModel model;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public MenuButtonModel getModel() {
        return model;
    }

    public void setModel(MenuButtonModel model) {
        this.model = model;
    }
}
