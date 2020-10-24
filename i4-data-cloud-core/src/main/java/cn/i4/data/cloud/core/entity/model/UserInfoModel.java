package cn.i4.data.cloud.core.entity.model;

import cn.i4.data.cloud.base.entity.model.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* Model
* @author wangjc
* @date 2020-10-10 10:14:12
*/
@TableName("t_user_info")
public class UserInfoModel extends BaseModel<UserInfoModel> {

    private static final long serialVersionUID = 1602296052210L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 1男，2女，3未知
    */
    @TableField("gender")
    private Integer gender;

    /**
    * 生日
    */
    @TableField("birthday")
    private String birthday;

    /**
    * 个性签名（50个字符以内）
    */
    @TableField("signature")
    private String signature;

    /**
    * 头像
    */
    @TableField("headimage")
    private String headimage;

    /**
    * 国家
    */
    @TableField("country")
    private String country;

    /**
    * 省
    */
    @TableField("province")
    private String province;

    /**
    * 城市
    */
    @TableField("city")
    private String city;

    /**
    * 区
    */
    @TableField("area")
    private String area;

    /**
    * 详细地址
    */
    @TableField("detail_address")
    private String detailAddress;

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


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setUserId(Integer userId) {
    this.userId = userId;
    }

    public Integer getUserId() {
    return this.userId;
    }

    public void setGender(Integer gender) {
    this.gender = gender;
    }

    public Integer getGender() {
    return this.gender;
    }

    public void setBirthday(String birthday) {
    this.birthday = birthday;
    }

    public String getBirthday() {
    return this.birthday;
    }

    public void setSignature(String signature) {
    this.signature = signature;
    }

    public String getSignature() {
    return this.signature;
    }

    public void setHeadimage(String headimage) {
    this.headimage = headimage;
    }

    public String getHeadimage() {
    return this.headimage;
    }

    public void setCountry(String country) {
    this.country = country;
    }

    public String getCountry() {
    return this.country;
    }

    public void setProvince(String province) {
    this.province = province;
    }

    public String getProvince() {
    return this.province;
    }

    public void setCity(String city) {
    this.city = city;
    }

    public String getCity() {
    return this.city;
    }

    public void setArea(String area) {
    this.area = area;
    }

    public String getArea() {
    return this.area;
    }

    public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
    return this.detailAddress;
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

}
