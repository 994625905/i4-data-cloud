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
* @date 2020-11-21 14:11:04
*/
@TableName("t_history_notice")
public class HistoryNoticeModel extends BaseModel<HistoryNoticeModel> {

    private static final long serialVersionUID = 1605939064265L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 标题
    */
    @TableField("title")
    private String title;

    /**
    * 类型
    */
    @TableField("type_id")
    private Integer typeId;

    /**
    * 简介信息
    */
    @TableField("explain_info")
    private String explainInfo;

    /**
    * 文本存储的mongoId
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

    /**
    * 发布时间
    */
    @TableField("deploy_time")
    private Long deployTime;

    /**
    * 创建人id
    */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
    * 0未发布，1已发布
    */
    @TableField("status")
    private Integer status;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
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

    public void setExplainInfo(String explainInfo) {
    this.explainInfo = explainInfo;
    }

    public String getExplainInfo() {
    return this.explainInfo;
    }

    public void setMongoId(String mongoId) {
    this.mongoId = mongoId;
    }

    public String getMongoId() {
    return this.mongoId;
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

    public void setDeployTime(Long deployTime) {
    this.deployTime = deployTime;
    }

    public Long getDeployTime() {
    return this.deployTime;
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

}
