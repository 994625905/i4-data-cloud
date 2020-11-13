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
* @date 2020-11-13 13:51:17
*/
@TableName("t_afternoon_tea_type")
public class AfternoonTeaTypeModel extends BaseModel<AfternoonTeaTypeModel> {

    private static final long serialVersionUID = 1605246677761L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 类型名称
    */
    @TableField("name")
    private String name;

    /**
    * 
    */
    @TableField("create_time")
    private Long createTime;

    /**
     * 创建者
     */
    @TableField("create_user_id")
    private Integer createUserId;

    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getName() {
    return this.name;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}
