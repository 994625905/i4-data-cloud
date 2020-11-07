package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-06 09:46:07
 */
public class WeekreportProcessView extends BaseView<WeekreportProcessView> {

	private static final long serialVersionUID = 1604627167198L;

	public WeekreportProcessView(WeekreportProcessModel model) {
		this.id = model.getId();
		this.weekreportId = model.getWeekreportId();
		this.processDefId = model.getProcessDefId();
		this.deploymentId = model.getDeploymentId();
		this.processInstanceId = model.getProcessInstanceId();
		this.userId = model.getUserId();
		this.status = model.getStatus();
		this.applyTime = model.getApplyTime();
		this.comment = model.getComment();
	}

	public WeekreportProcessView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("weekreport_id")
	private Integer weekreportId;

	/**
	 * 部署流程定义的id
	 */
	@TableField("process_def_id")
	private String processDefId;

	/**
	 * 部署流程的单元Id
	 */
	@TableField("deployment_id")
	private String deploymentId;

	/**
	 * 流程实例id
	 */
	@TableField("process_instance_id")
	private String processInstanceId;

	/**
	 * 周报流程提交的user
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 0审批中，1已通过，2未通过
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 申请时间
	 */
	@TableField("apply_time")
	private Long applyTime;

	/**
	 * 备注解释说明
	 */
	@TableField("comment")
	private String comment;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setWeekreportId(Integer weekreportId) {
		this.weekreportId = weekreportId;
	}

	public Integer getWeekreportId() {
		return this.weekreportId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getProcessDefId() {
		return this.processDefId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getDeploymentId() {
		return this.deploymentId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessInstanceId() {
		return this.processInstanceId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setApplyTime(Long applyTime) {
		this.applyTime = applyTime;
	}

	public Long getApplyTime() {
		return this.applyTime;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return this.comment;
	}

}
