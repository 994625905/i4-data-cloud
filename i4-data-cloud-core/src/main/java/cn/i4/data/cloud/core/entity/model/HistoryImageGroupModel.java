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
@TableName("t_history_image_group")
public class HistoryImageGroupModel extends BaseModel<HistoryImageGroupModel> {

    private static final long serialVersionUID = 1605786424884L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 创建者
    */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
    * 名称
    */
    @TableField("name")
    private String name;

    /**
    * 描述信息
    */
    @TableField("describe_info")
    private String describeInfo;

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

    public void setDescribeInfo(String describeInfo) {
    this.describeInfo = describeInfo;
    }

    public String getDescribeInfo() {
    return this.describeInfo;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

}
