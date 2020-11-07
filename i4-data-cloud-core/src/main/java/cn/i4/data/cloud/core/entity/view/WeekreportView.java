package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.WeekreportModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-06 09:46:07
 */
public class WeekreportView extends BaseView<WeekreportView> {

	private static final long serialVersionUID = 1604627167257L;

	public WeekreportView(WeekreportModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.title = model.getTitle();
		this.year = model.getYear();
		this.week = model.getWeek();
		this.startDate = model.getStartDate();
		this.endDate = model.getEndDate();
		this.mongoId = model.getMongoId();
		this.enclosure = model.getEnclosure();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
	}

	public WeekreportView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 标题
	 */
	@TableField("title")
	private String title;

	/**
	 * 年份
	 */
	@TableField("year")
	private Integer year;

	/**
	 * 第几周
	 */
	@TableField("week")
	private Integer week;

	/**
	 * 开始时间，MM-DD
	 */
	@TableField("start_date")
	private String startDate;

	/**
	 * 结束时间
	 */
	@TableField("end_date")
	private String endDate;

	/**
	 * 富文本存储的MongoId
	 */
	@TableField("mongo_id")
	private String mongoId;

	/**
	 * 附加地址，非必填项
	 */
	@TableField("enclosure")
	private String enclosure;

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

	/** 补充字段 */
	private String createTimeStr;
	private String updateTimeStr;
	private String realName;
	private String processId;
	private String processStatus;
	/** 流程的相关字段和下一节点的代办人 */
	private String processDefId;
	private String deploymentId;
	private String processInstanceId;
	
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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getWeek() {
		return this.week;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public String getMongoId() {
		return mongoId;
	}

	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getEnclosure() {
		return this.enclosure;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
}
