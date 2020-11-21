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
* @date 2020-11-06 09:46:07
*/
@TableName("t_weekreport")
public class WeekreportModel extends BaseModel<WeekreportModel> {

    private static final long serialVersionUID = 1604627167254L;
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
    * 标题
    */
    @TableField("title")
    private String title;

    /**
    * 年份
    */
    @TableField("year")
    private Integer year;

    /**
    * 第几周
    */
    @TableField("week")
    private Integer week;

    /**
    * 开始时间，MM-DD
    */
    @TableField("start_date")
    private String startDate;

    /**
    * 结束时间
    */
    @TableField("end_date")
    private String endDate;

    /**
    * 富文本存储的MongoId
    */
    @TableField("mongo_id")
    private String mongoId;

    /**
    * 
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 
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

    public void setYear(Integer year) {
    this.year = year;
    }

    public Integer getYear() {
    return this.year;
    }

    public void setWeek(Integer week) {
    this.week = week;
    }

    public Integer getWeek() {
    return this.week;
    }

    public void setStartDate(String startDate) {
    this.startDate = startDate;
    }

    public String getStartDate() {
    return this.startDate;
    }

    public void setEndDate(String endDate) {
    this.endDate = endDate;
    }

    public String getEndDate() {
    return this.endDate;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
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
