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
* @date 2020-11-06 15:24:53
*/
@TableName("t_user_template")
public class UserTemplateModel extends BaseModel<UserTemplateModel> {

    private static final long serialVersionUID = 1604647493118L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 用户id
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 周报标题模板
    */
    @TableField("weekreport_title")
    private String weekreportTitle;


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

    public void setWeekreportTitle(String weekreportTitle) {
    this.weekreportTitle = weekreportTitle;
    }

    public String getWeekreportTitle() {
    return this.weekreportTitle;
    }

}
