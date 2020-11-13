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
* @date 2020-11-10 15:14:47
*/
@TableName("t_afternoon_tea")
public class AfternoonTeaModel extends BaseModel<AfternoonTeaModel> {

    private static final long serialVersionUID = 1604992487028L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    @TableField("type_id")
    private Integer typeId;

    /**
    * 名称
    */
    @TableField("name")
    private String name;

    /**
    * 图片链接
    */
    @TableField("image")
    private String image;

    /**
    * 单价
    */
    @TableField("price")
    private java.math.BigDecimal price;

    /**
    * 商店地址（简称也行）
    */
    @TableField("store_address")
    private String storeAddress;

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
    * 创建者
    */
    @TableField("create_user_id")
    private Integer createUserId;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getName() {
    return this.name;
    }

    public void setImage(String image) {
    this.image = image;
    }

    public String getImage() {
    return this.image;
    }

    public void setPrice(java.math.BigDecimal price) {
    this.price = price;
    }

    public java.math.BigDecimal getPrice() {
    return this.price;
    }

    public void setStoreAddress(String storeAddress) {
    this.storeAddress = storeAddress;
    }

    public String getStoreAddress() {
    return this.storeAddress;
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

}
