package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.RoleMenuModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-11 13:42:25
 */
public class RoleMenuView extends BaseView<RoleMenuView> {

	private static final long serialVersionUID = 1602394945559L;

	public RoleMenuView(RoleMenuModel model) {
		this.id = model.getId();
		this.roleId = model.getRoleId();
		this.menuButtonId = model.getMenuButtonId();
	}

	public RoleMenuView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("role_id")
	private Integer roleId;

	/**
	 * 
	 */
	@TableField("menu_button_id")
	private Integer menuButtonId;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setMenuButtonId(Integer menuButtonId) {
		this.menuButtonId = menuButtonId;
	}

	public Integer getMenuButtonId() {
		return this.menuButtonId;
	}

}
