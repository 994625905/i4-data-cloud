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
* @date 2020-10-31 17:10:53
*/
@TableName("t_invite_code_department")
public class InviteCodeDepartmentModel extends BaseModel<InviteCodeDepartmentModel> {

    private static final long serialVersionUID = 1604135453105L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 邀请码ID
    */
    @TableField("invite_code_id")
    private Integer inviteCodeId;

    /**
    * 部门ID
    */
    @TableField("department_id")
    private Integer departmentId;


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

    public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
    }

    public Integer getDepartmentId() {
    return this.departmentId;
    }

}
