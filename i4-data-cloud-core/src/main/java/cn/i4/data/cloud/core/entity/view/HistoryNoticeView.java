package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-21 14:11:04
 */
public class HistoryNoticeView extends BaseView<HistoryNoticeView> {

	private static final long serialVersionUID = 1605939064269L;

	public HistoryNoticeView(HistoryNoticeModel model) {
		this.id = model.getId();
		this.title = model.getTitle();
		this.typeId = model.getTypeId();
		this.explainInfo = model.getExplainInfo();
		this.mongoId = model.getMongoId();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
		this.deployTime = model.getDeployTime();
		this.createUserId = model.getCreateUserId();
		this.status = model.getStatus();
	}

	public HistoryNoticeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 标题
	 */
	@TableField("title")
	private String title;

	/**
	 * 类型
	 */
	@TableField("type_id")
	private Integer typeId;

	/**
	 * 简介信息
	 */
	@TableField("explain_info")
	private String explainInfo;

	/**
	 * 文本存储的mongoId
	 */
	@TableField("mongo_id")
	private String mongoId;

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
	 * 发布时间
	 */
	@TableField("deploy_time")
	private Long deployTime;

	/**
	 * 创建人id
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 0未发布，1已发布
	 */
	@TableField("status")
	private Integer status;

	/** 补充字段 */
	private String typeName;
	private String createTimeStr;
	private String updateTimeStr;
	private String deployTimeStr;
	private String createUserName;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setExplainInfo(String explainInfo) {
		this.explainInfo = explainInfo;
	}

	public String getExplainInfo() {
		return this.explainInfo;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public String getMongoId() {
		return this.mongoId;
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

	public void setDeployTime(Long deployTime) {
		this.deployTime = deployTime;
	}

	public Long getDeployTime() {
		return this.deployTime;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public String getDeployTimeStr() {
		return deployTimeStr;
	}

	public void setDeployTimeStr(String deployTimeStr) {
		this.deployTimeStr = deployTimeStr;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}
