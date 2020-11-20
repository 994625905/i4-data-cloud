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
@TableName("t_history_image_read")
public class HistoryImageReadModel extends BaseModel<HistoryImageReadModel> {

    private static final long serialVersionUID = 1605786424915L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 
    */
    @TableField("image_id")
    private Integer imageId;

    /**
    * 
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

}
