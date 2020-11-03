package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.LeaveProcessCcModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-03 18:13:38
 */
public class LeaveProcessCcView extends BaseView<LeaveProcessCcView> {

	private static final long serialVersionUID = 1604398418671L;

	public LeaveProcessCcView(LeaveProcessCcModel model) {
		this.id = model.getId();
		this.leaveId = model.getLeaveId();
		this.leaveProcessId = model.getLeaveProcessId();
		this.userId = model.getUserId();
		this.read = model.getRead();
	}

	public LeaveProcessCcView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 请假条id
	 */
	@TableField("leave_id")
	private Integer leaveId;

	/**
	 * 请假流程ID
	 */
	@TableField("leave_process_id")
	private Integer leaveProcessId;

	/**
	 * 抄送用户id
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 0未阅读，1已阅读
	 */
	@TableField("read")
	private Integer read;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Integer getLeaveId() {
		return this.leaveId;
	}

	public void setLeaveProcessId(Integer leaveProcessId) {
		this.leaveProcessId = leaveProcessId;
	}

	public Integer getLeaveProcessId() {
		return this.leaveProcessId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	public Integer getRead() {
		return this.read;
	}

}
