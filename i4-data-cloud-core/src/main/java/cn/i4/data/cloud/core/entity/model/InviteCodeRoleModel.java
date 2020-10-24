package cn.i4.data.cloud.core.entity.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.i4.data.cloud.base.entity.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* Model
* @author wangjc
* @date 2020-10-18 14:34:30
*/
@TableName("t_invite_code_role")
public class InviteCodeRoleModel extends BaseModel<InviteCodeRoleModel> {

    private static final long serialVersionUID = 1603002870687L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 
    */
    @TableField("invite_code_id")
    private Integer inviteCodeId;

    /**
    * 
    */
    @TableField("role_id")
    private Integer roleId;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setInviteCodeId(Integer inviteCodeId) {
    this.inviteCodeId = inviteCodeId;
    }

    public Integer getInviteCodeId() {
    return this.inviteCodeId;
    }

    public void setRoleId(Integer roleId) {
    this.roleId = roleId;
    }

    public Integer getRoleId() {
    return this.roleId;
    }

}
