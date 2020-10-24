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
* @date 2020-10-14 14:43:30
*/
@TableName("t_system_constant")
public class SystemConstantModel extends BaseModel<SystemConstantModel> {

    private static final long serialVersionUID = 1602657810745L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 常量名称
    */
    @TableField("constant_name")
    private String constantName;

    /**
    * 常量key
    */
    @TableField("constant_key")
    private String constantKey;

    /**
    * 常量值
    */
    @TableField("constant_value")
    private String constantValue;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setConstantName(String constantName) {
    this.constantName = constantName;
    }

    public String getConstantName() {
    return this.constantName;
    }

    public void setConstantKey(String constantKey) {
    this.constantKey = constantKey;
    }

    public String getConstantKey() {
    return this.constantKey;
    }

    public void setConstantValue(String constantValue) {
    this.constantValue = constantValue;
    }

    public String getConstantValue() {
    return this.constantValue;
    }

}
