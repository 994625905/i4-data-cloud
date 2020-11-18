package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.PartyActivityModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-14 14:20:33
 */
public class PartyActivityView extends BaseView<PartyActivityView> {

	private static final long serialVersionUID = 1605334833859L;

	public PartyActivityView(PartyActivityModel model) {
		this.id = model.getId();
		this.typeId = model.getTypeId();
		this.headUserId = model.getHeadUserId();
		this.title = model.getTitle();
		this.coverImage = model.getCoverImage();
		this.mongoId = model.getMongoId();
		this.startTime = model.getStartTime();
		this.endTime = model.getEndTime();
		this.address = model.getAddress();
		this.isSign = model.getIsSign();
		this.signStartTime = model.getSignStartTime();
		this.signEndTime = model.getSignEndTime();
		this.limitSign = model.getLimitSign();
		this.trafficType = model.getTrafficType();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
		this.createUserId = model.getCreateUserId();
	}

	public PartyActivityView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
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

	/** 补充字段 */
	private String startTimeStr;
	private String endTimeStr;
	private String signEndTimeStr;
	private String signStartTimeStr;
	private String createTimeStr;
	private String updateTimeStr;
	private String headUserName;
	private String typeName;
	private Integer userSign;

	/** 照片墙 */
	private String imageCover;
	private Integer imageCount;

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

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getSignEndTimeStr() {
		return signEndTimeStr;
	}

	public void setSignEndTimeStr(String signEndTimeStr) {
		this.signEndTimeStr = signEndTimeStr;
	}

	public String getSignStartTimeStr() {
		return signStartTimeStr;
	}

	public void setSignStartTimeStr(String signStartTimeStr) {
		this.signStartTimeStr = signStartTimeStr;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getHeadUserName() {
		return headUserName;
	}

	public void setHeadUserName(String headUserName) {
		this.headUserName = headUserName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getUserSign() {
		return userSign;
	}

	public void setUserSign(Integer userSign) {
		this.userSign = userSign;
	}

	public String getImageCover() {
		return imageCover;
	}

	public void setImageCover(String imageCover) {
		this.imageCover = imageCover;
	}

	public Integer getImageCount() {
		return imageCount;
	}

	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}
}
