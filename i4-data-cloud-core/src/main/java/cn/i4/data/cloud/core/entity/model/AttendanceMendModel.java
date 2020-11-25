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
* @date 2020-11-23 13:55:44
*/
@TableName("t_attendance_mend")
public class AttendanceMendModel extends BaseModel<AttendanceMendModel> {

    private static final long serialVersionUID = 1606110944675L;
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
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 打卡日id
    */
    @TableField("attendance_day_id")
    private Integer attendanceDayId;

    /**
    * 补卡原因
    */
    @TableField("reason")
    private String reason;


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

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setAttendanceDayId(Integer attendanceDayId) {
    this.attendanceDayId = attendanceDayId;
    }

    public Integer getAttendanceDayId() {
    return this.attendanceDayId;
    }

    public void setReason(String reason) {
    this.reason = reason;
    }

    public String getReason() {
    return this.reason;
    }

}
