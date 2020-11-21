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
* @date 2020-11-01 14:58:27
*/
@TableName("t_leave")
public class LeaveModel extends BaseModel<LeaveModel> {

    private static final long serialVersionUID = 1604213907811L;
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
    * 请假标题
    */
    @TableField("title")
    private String title;

    /**
    * 请假类型
    */
    @TableField("type_id")
    private Integer typeId;

    /**
    * 请假原因
    */
    @TableField("reason")
    private String reason;

    /**
    * 开始时间
    */
    @TableField("start_time")
    private Long startTime;

    /**
    * 结束时间
    */
    @TableField("end_time")
    private Long endTime;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 最后修改时间
    */
    @TableField("update_time")
    private Long updateTime;


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

    public void setTitle(String title) {
    this.title = title;
    }

    public String getTitle() {
    return this.title;
    }

    public void setTypeId(Integer typeId) {
    this.typeId = typeId;
    }

    public Integer getTypeId() {
    return this.typeId;
    }

    public void setReason(String reason) {
    this.reason = reason;
    }

    public String getReason() {
    return this.reason;
    }

    public void setStartTime(Long startTime) {
    this.startTime = startTime;
    }

    public Long getStartTime() {
    return this.startTime;
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

    public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    }

    public Long getUpdateTime() {
    return this.updateTime;
    }

}
