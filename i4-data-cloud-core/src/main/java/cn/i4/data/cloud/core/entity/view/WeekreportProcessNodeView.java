package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessNodeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-06 09:46:07
 */
public class WeekreportProcessNodeView extends BaseView<WeekreportProcessNodeView> {

	private static final long serialVersionUID = 1604627167240L;

	public WeekreportProcessNodeView(WeekreportProcessNodeModel model) {
		this.id = model.getId();
		this.weekreportProcessId = model.getWeekreportProcessId();
		this.upperId = model.getUpperId();
		this.nodeName = model.getNodeName();
		this.userId = model.getUserId();
		this.receiveUserId = model.getReceiveUserId();
		this.dealTime = model.getDealTime();
		this.dealType = model.getDealType();
		this.comment = model.getComment();
	}

	public WeekreportProcessNodeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 周报流程id
	 */
	@TableField("weekreport_process_id")
	private Integer weekreportProcessId;

	/**
	 * 上级节点id
	 */
	@TableField("upper_id")
	private Integer upperId;

	/**
	 * 节点名称
	 */
	@TableField("node_name")
	private String nodeName;

	/**
	 * 当前节点的办理人
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 下级节点的接收人
	 */
	@TableField("receive_user_id")
	private Integer receiveUserId;

	/**
	 * 办理时间
	 */
	@TableField("deal_time")
	private Long dealTime;

	/**
	 * 办理类型，0拒绝，1放行，2驳回
	 */
	@TableField("deal_type")
	private Integer dealType;

	/**
	 * 备注解释
	 */
	@TableField("comment")
	private String comment;

	/** 补充字段 */
	private String dealTimeStr;
	private String userName;
	private String receiveUserName;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setWeekreportProcessId(Integer weekreportProcessId) {
		this.weekreportProcessId = weekreportProcessId;
	}

	public Integer getWeekreportProcessId() {
		return this.weekreportProcessId;
	}

	public void setUpperId(Integer upperId) {
		this.upperId = upperId;
	}

	public Integer getUpperId() {
		return this.upperId;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setReceiveUserId(Integer receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public Integer getReceiveUserId() {
		return this.receiveUserId;
	}

	public void setDealTime(Long dealTime) {
		this.dealTime = dealTime;
	}

	public Long getDealTime() {
		return this.dealTime;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

	public Integer getDealType() {
		return this.dealType;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return this.comment;
	}

	public String getDealTimeStr() {
		return dealTimeStr;
	}

	public void setDealTimeStr(String dealTimeStr) {
		this.dealTimeStr = dealTimeStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}
}
