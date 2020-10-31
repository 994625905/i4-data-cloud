package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.InviteCodeDepartmentModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-31 17:10:53
 */
public class InviteCodeDepartmentView extends BaseView<InviteCodeDepartmentView> {

	private static final long serialVersionUID = 1604135453116L;

	public InviteCodeDepartmentView(InviteCodeDepartmentModel model) {
		this.id = model.getId();
		this.inviteCodeId = model.getInviteCodeId();
		this.departmentId = model.getDepartmentId();
	}

	public InviteCodeDepartmentView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 邀请码ID
	 */
	@TableField("invite_code_id")
	private Integer inviteCodeId;

	/**
	 * 部门ID
	 */
	@TableField("department_id")
	private Integer departmentId;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setInviteCodeId(Integer inviteCodeId) {
		this.inviteCodeId = inviteCodeId;
	}

	public Integer getInviteCodeId() {
		return this.inviteCodeId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

}
