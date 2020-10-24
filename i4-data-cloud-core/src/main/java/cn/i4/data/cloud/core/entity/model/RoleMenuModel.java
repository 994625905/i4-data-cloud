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
* @date 2020-10-11 13:42:25
*/
@TableName("t_role_menu")
public class RoleMenuModel extends BaseModel<RoleMenuModel> {

    private static final long serialVersionUID = 1602394945551L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 
    */
    @TableField("role_id")
    private Integer roleId;

    /**
    * 
    */
    @TableField("menu_button_id")
    private Integer menuButtonId;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setRoleId(Integer roleId) {
    this.roleId = roleId;
    }

    public Integer getRoleId() {
    return this.roleId;
    }

    public void setMenuButtonId(Integer menuButtonId) {
    this.menuButtonId = menuButtonId;
    }

    public Integer getMenuButtonId() {
    return this.menuButtonId;
    }

}
