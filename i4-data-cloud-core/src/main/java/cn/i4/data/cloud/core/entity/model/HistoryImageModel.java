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
* @date 2020-11-19 19:47:04
*/
@TableName("t_history_image")
public class HistoryImageModel extends BaseModel<HistoryImageModel> {

    private static final long serialVersionUID = 1605786424770L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 照片组id
    */
    @TableField("group_id")
    private Integer groupId;

    /**
    * 照片名称
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
    * 文件大小(单位：KB，上传有大小限制)
    */
    @TableField("size")
    private Float size;

    /**
    * 图片宽度
    */
    @TableField("width")
    private Integer width;

    /**
    * 图片高度
    */
    @TableField("height")
    private Integer height;

    /**
    * 创建人
    */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
    * 
    */
    @TableField("create_time")
    private Long createTime;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setGroupId(Integer groupId) {
    this.groupId = groupId;
    }

    public Integer getGroupId() {
    return this.groupId;
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

    public void setSize(Float size) {
    this.size = size;
    }

    public Float getSize() {
    return this.size;
    }

    public void setWidth(Integer width) {
    this.width = width;
    }

    public Integer getWidth() {
    return this.width;
    }

    public void setHeight(Integer height) {
    this.height = height;
    }

    public Integer getHeight() {
    return this.height;
    }

    public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
    }

    public Integer getCreateUserId() {
    return this.createUserId;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

}
