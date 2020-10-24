package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-10-18 14:34:31
*/
public class InviteCodeDto extends BaseDto<InviteCodeView> {

    private static final long serialVersionUID = 1603002871226L;

    private Integer id;
    private String code;

    private InviteCodeModel model;
    private List<Integer> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public InviteCodeModel getModel() {
        return model;
    }

    public void setModel(InviteCodeModel model) {
        this.model = model;
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }
}
