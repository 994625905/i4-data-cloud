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
@TableName("t_attendance")
public class AttendanceModel extends BaseModel<AttendanceModel> {

    private static final long serialVersionUID = 1606110944565L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 打卡用户
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 打卡时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 打卡日期
    */
    @TableField("create_date")
    private String createDate;


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

    public void setCreateDate(String createDate) {
    this.createDate = createDate;
    }

    public String getCreateDate() {
    return this.createDate;
    }

}
