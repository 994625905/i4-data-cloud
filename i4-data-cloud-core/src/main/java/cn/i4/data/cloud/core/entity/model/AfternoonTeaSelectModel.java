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
* @date 2020-11-10 19:54:04
*/
@TableName("t_afternoon_tea_select")
public class AfternoonTeaSelectModel extends BaseModel<AfternoonTeaSelectModel> {

    private static final long serialVersionUID = 1605009244123L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 下午茶任务id
    */
    @TableField("tea_task_id")
    private Integer teaTaskId;

    /**
    * 下午茶点单Id
    */
    @TableField("tea_menu_id")
    private Integer teaMenuId;

    /**
    * 下午茶id
    */
    @TableField("tea_id")
    private Integer teaId;

    /**
    * 点单时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 点单用户
    */
    @TableField("select_user_id")
    private Integer selectUserId;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setTeaTaskId(Integer teaTaskId) {
    this.teaTaskId = teaTaskId;
    }

    public Integer getTeaTaskId() {
    return this.teaTaskId;
    }

    public void setTeaMenuId(Integer teaMenuId) {
    this.teaMenuId = teaMenuId;
    }

    public Integer getTeaMenuId() {
    return this.teaMenuId;
    }

    public void setTeaId(Integer teaId) {
    this.teaId = teaId;
    }

    public Integer getTeaId() {
    return this.teaId;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setSelectUserId(Integer selectUserId) {
    this.selectUserId = selectUserId;
    }

    public Integer getSelectUserId() {
    return this.selectUserId;
    }

}
