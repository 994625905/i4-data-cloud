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
@TableName("t_history_image_like")
public class HistoryImageLikeModel extends BaseModel<HistoryImageLikeModel> {

    private static final long serialVersionUID = 1605786424899L;
    /**
    * 
    */
    @TableField("id")
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
    * 1点赞，-1取赞
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

    public void setType(Integer type) {
    this.type = type;
    }

    public Integer getType() {
    return this.type;
    }

}
