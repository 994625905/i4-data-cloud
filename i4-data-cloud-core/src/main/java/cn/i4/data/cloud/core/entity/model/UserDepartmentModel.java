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
* @date 2020-10-31 16:11:56
*/
@TableName("t_user_department")
public class UserDepartmentModel extends BaseModel<UserDepartmentModel> {

    private static final long serialVersionUID = 1604131916139L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 
    */
    @TableField("department_id")
    private Integer departmentId;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setUserId(Integer userId) {
    this.userId = userId;
    }

    public Integer getUserId() {
    return this.userId;
    }

    public void setDepartmentId(Integer departmentId) {
    this.departmentId = departmentId;
    }

    public Integer getDepartmentId() {
    return this.departmentId;
    }

}
