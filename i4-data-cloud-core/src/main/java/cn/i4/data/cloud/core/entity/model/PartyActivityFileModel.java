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
* @date 2020-11-14 15:39:24
*/
@TableName("t_party_activity_file")
public class PartyActivityFileModel extends BaseModel<PartyActivityFileModel> {

    private static final long serialVersionUID = 1605339564774L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 活动id
    */
    @TableField("activity_id")
    private Integer activityId;

    /**
    * 创建用户
    */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
    * 名称
    */
    @TableField("name")
    private String name;

    /**
    * 在线链接
    */
    @TableField("url")
    private String url;

    /**
    * 后缀类型
    */
    @TableField("suffix")
    private String suffix;

    /**
    * 1图片 2音频 3视频 4文档 5其他
    */
    @TableField("type")
    private Integer type;

    /**
    * 文件大小(单位：KB，上传有大小限制)
    */
    @TableField("size")
    private Float size;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setActivityId(Integer activityId) {
    this.activityId = activityId;
    }

    public Integer getActivityId() {
    return this.activityId;
    }

    public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
    }

    public Integer getCreateUserId() {
    return this.createUserId;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getName() {
    return this.name;
    }

    public void setUrl(String url) {
    this.url = url;
    }

    public String getUrl() {
    return this.url;
    }

    public void setSuffix(String suffix) {
    this.suffix = suffix;
    }

    public String getSuffix() {
    return this.suffix;
    }

    public void setType(Integer type) {
    this.type = type;
    }

    public Integer getType() {
    return this.type;
    }

    public void setSize(Float size) {
    this.size = size;
    }

    public Float getSize() {
    return this.size;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

}
