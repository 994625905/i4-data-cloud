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
* @date 2020-11-14 14:20:33
*/
@TableName("t_party_activity")
public class PartyActivityModel extends BaseModel<PartyActivityModel> {

    private static final long serialVersionUID = 1605334833762L;
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
    * 活动负责人
    */
    @TableField("head_user_id")
    private Integer headUserId;

    /**
    * 标题
    */
    @TableField("title")
    private String title;

    /**
    * 封面头像
    */
    @TableField("cover_image")
    private String coverImage;

    /**
    * 内容存储的mongoId
    */
    @TableField("mongo_id")
    private String mongoId;

    /**
    * 开始时间
    */
    @TableField("start_time")
    private Long startTime;

    /**
    * 结束时间
    */
    @TableField("end_time")
    private Long endTime;

    /**
     * 活动地址
     */
    @TableField("address")
    private String address;

    /**
    * 是否需要签到参与，0否，1是
    */
    @TableField("is_sign")
    private Integer isSign;

    /**
     * 开始报名时间
     */
    @TableField("sign_start_time")
    private Long signStartTime;

    /**
    * 截止报名时间
    */
    @TableField("sign_end_time")
    private Long signEndTime;

    /**
    * 限制携带家属，0允许，1禁止
    */
    @TableField("limit_sign")
    private Integer limitSign;

    /**
    * 交通方式，0自行前往，1集体乘车
    */
    @TableField("traffic_type")
    private Integer trafficType;

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
    * 创建者
    */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
     * 状态：0未发布，1已发布，2已过期（过期根据end_time判断）
     */
    @TableField("status")
    private Integer status;

    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setTypeId(Integer typeId) {
    this.typeId = typeId;
    }

    public Integer getTypeId() {
    return this.typeId;
    }

    public void setHeadUserId(Integer headUserId) {
    this.headUserId = headUserId;
    }

    public Integer getHeadUserId() {
    return this.headUserId;
    }

    public void setTitle(String title) {
    this.title = title;
    }

    public String getTitle() {
    return this.title;
    }

    public void setCoverImage(String coverImage) {
    this.coverImage = coverImage;
    }

    public String getCoverImage() {
    return this.coverImage;
    }

    public void setMongoId(String mongoId) {
    this.mongoId = mongoId;
    }

    public String getMongoId() {
    return this.mongoId;
    }

    public void setStartTime(Long startTime) {
    this.startTime = startTime;
    }

    public Long getStartTime() {
    return this.startTime;
    }

    public void setEndTime(Long endTime) {
    this.endTime = endTime;
    }

    public Long getEndTime() {
    return this.endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    public Long getSignStartTime() {
        return signStartTime;
    }

    public void setSignStartTime(Long signStartTime) {
        this.signStartTime = signStartTime;
    }

    public Long getSignEndTime() {
        return signEndTime;
    }

    public void setSignEndTime(Long signEndTime) {
        this.signEndTime = signEndTime;
    }

    public void setLimitSign(Integer limitSign) {
    this.limitSign = limitSign;
    }

    public Integer getLimitSign() {
    return this.limitSign;
    }

    public void setTrafficType(Integer trafficType) {
    this.trafficType = trafficType;
    }

    public Integer getTrafficType() {
    return this.trafficType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
