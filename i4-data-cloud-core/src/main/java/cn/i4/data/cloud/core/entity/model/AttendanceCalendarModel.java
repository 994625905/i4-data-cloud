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
* @date 2020-11-24 20:43:55
*/
@TableName("t_attendance_calendar")
public class AttendanceCalendarModel extends BaseModel<AttendanceCalendarModel> {

    private static final long serialVersionUID = 1606221835400L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 具体年份
    */
    @TableField("year")
    private Integer year;

    /**
    * 月
    */
    @TableField("month")
    private Integer month;

    /**
    * 周几
    */
    @TableField("week")
    private String week;

    /**
    * 假日名称，type=2有效
    */
    @TableField("holiday_name")
    private String holidayName;

    /**
    * 日期，yyyyMMDD
    */
    @TableField("date")
    private String date;

    /**
    * 2法定节假日，3法定工作日
    */
    @TableField("type")
    private Integer type;

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

    public void setYear(Integer year) {
    this.year = year;
    }

    public Integer getYear() {
    return this.year;
    }

    public void setMonth(Integer month) {
    this.month = month;
    }

    public Integer getMonth() {
    return this.month;
    }

    public void setWeek(String week) {
    this.week = week;
    }

    public String getWeek() {
    return this.week;
    }

    public void setHolidayName(String holidayName) {
    this.holidayName = holidayName;
    }

    public String getHolidayName() {
    return this.holidayName;
    }

    public void setDate(String date) {
    this.date = date;
    }

    public String getDate() {
    return this.date;
    }

    public void setType(Integer type) {
    this.type = type;
    }

    public Integer getType() {
    return this.type;
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
