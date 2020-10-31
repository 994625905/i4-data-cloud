package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.UserDepartmentModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-31 16:11:56
 */
public class UserDepartmentView extends BaseView<UserDepartmentView> {

	private static final long serialVersionUID = 1604131916220L;

	public UserDepartmentView(UserDepartmentModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.departmentId = model.getDepartmentId();
	}

	public UserDepartmentView() {
		
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
	@TableField("department_id")
	private Integer departmentId;

	
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

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

}
