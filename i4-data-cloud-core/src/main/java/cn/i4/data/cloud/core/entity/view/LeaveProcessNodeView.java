package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.LeaveProcessNodeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-01 14:58:27
 */
public class LeaveProcessNodeView extends BaseView<LeaveProcessNodeView> {

	private static final long serialVersionUID = 1604213907774L;

	public LeaveProcessNodeView(LeaveProcessNodeModel model) {
		this.id = model.getId();
		this.leaveProcessId = model.getLeaveProcessId();
		this.upperId = model.getUpperId();
		this.nodeName = model.getNodeName();
		this.userId = model.getUserId();
		this.receiveUserId = model.getReceiveUserId();
		this.dealTime = model.getDealTime();
		this.dealType = model.getDealType();
		this.comment = model.getComment();
	}

	public LeaveProcessNodeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 请假流程id
	 */
	@TableField("leave_process_id")
	private Integer leaveProcessId;

	/**
	 * 
	 */
	@TableField("upper_id")
	private Integer upperId;

	/**
	 * 
	 */
	@TableField("node_name")
	private String nodeName;

	/**
	 * 当前节点的创建人
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 
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

	public void setLeaveProcessId(Integer leaveProcessId) {
		this.leaveProcessId = leaveProcessId;
	}

	public Integer getLeaveProcessId() {
		return this.leaveProcessId;
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
