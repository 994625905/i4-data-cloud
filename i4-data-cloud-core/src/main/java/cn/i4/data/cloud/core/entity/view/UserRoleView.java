package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.UserRoleModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-11 13:42:25
 */
public class UserRoleView extends BaseView<UserRoleView> {

	private static final long serialVersionUID = 1602394945179L;

	public UserRoleView(UserRoleModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.roleId = model.getRoleId();
	}

	public UserRoleView() {
		
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

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

}
