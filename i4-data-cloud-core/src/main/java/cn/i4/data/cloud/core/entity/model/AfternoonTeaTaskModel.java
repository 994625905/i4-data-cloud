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
@TableName("t_afternoon_tea_task")
public class AfternoonTeaTaskModel extends BaseModel<AfternoonTeaTaskModel> {

    private static final long serialVersionUID = 1605009244054L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
    * 结束时间，过期无法进入
    */
    @TableField("end_time")
    private Long endTime;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 修改时间
    */
    @TableField("update_time")
    private Long updateTime;

    /**
    * 创建时间
    */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
    * 0未发布，1已发布，2已过期
    */
    @TableField("status")
    private Integer status;

    /**
     * 描述信息
     */
    @TableField("describe_info")
    private String describeInfo;

    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
    }

    public Integer getCreateUserId() {
    return this.createUserId;
    }

    public void setStatus(Integer status) {
    this.status = status;
    }

    public Integer getStatus() {
    return this.status;
    }

    public String getDescribeInfo() {
        return describeInfo;
    }

    public void setDescribeInfo(String describeInfo) {
        this.describeInfo = describeInfo;
    }
}
