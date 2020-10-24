package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.InviteCodeRoleModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-18 14:34:30
 */
public class InviteCodeRoleView extends BaseView<InviteCodeRoleView> {

	private static final long serialVersionUID = 1603002871022L;

	public InviteCodeRoleView(InviteCodeRoleModel model) {
		this.id = model.getId();
		this.inviteCodeId = model.getInviteCodeId();
		this.roleId = model.getRoleId();
	}

	public InviteCodeRoleView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("invite_code_id")
	private Integer inviteCodeId;

	/**
	 * 
	 */
	@TableField("role_id")
	private Integer roleId;

	
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

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

}
