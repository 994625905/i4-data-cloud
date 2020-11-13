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
* @date 2020-11-11 11:41:50
*/
@TableName("t_afternoon_tea_menu")
public class AfternoonTeaMenuModel extends BaseModel<AfternoonTeaMenuModel> {

    private static final long serialVersionUID = 1605066110225L;
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
    * 下午茶id的列表字符串，逗号隔开
    */
    @TableField("tea_ids")
    private String teaIds;

    /**
    * 日期
    */
    @TableField("date")
    private String date;

    /**
    * 周几
    */
    @TableField("week")
    private String week;

    /**
    * 结束时间，过期无法点单
    */
    @TableField("end_time")
    private Long endTime;

    /**
    * 
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 
    */
    @TableField("create_user_id")
    private Integer createUserId;

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

    public void setTeaIds(String teaIds) {
    this.teaIds = teaIds;
    }

    public String getTeaIds() {
    return this.teaIds;
    }

    public void setDate(String date) {
    this.date = date;
    }

    public String getDate() {
    return this.date;
    }

    public void setWeek(String week) {
    this.week = week;
    }

    public String getWeek() {
    return this.week;
    }

    public void setEndTime(Long endTime) {
    this.endTime = endTime;
    }

    public Long getEndTime() {
    return this.endTime;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
    }

    public Integer getCreateUserId() {
    return this.createUserId;
    }

}
