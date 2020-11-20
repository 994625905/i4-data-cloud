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
* @date 2020-11-18 10:11:37
*/
@TableName("t_party_activity_image_like")
public class PartyActivityImageLikeModel extends BaseModel<PartyActivityImageLikeModel> {

    private static final long serialVersionUID = 1605665497186L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 照片id
    */
    @TableField("image_id")
    private Integer imageId;

    /**
    * 
    */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
     * 点赞类型：1点赞，-1取赞
     */
    @TableField("type")
    private Integer type;

    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setImageId(Integer imageId) {
    this.imageId = imageId;
    }

    public Integer getImageId() {
    return this.imageId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
