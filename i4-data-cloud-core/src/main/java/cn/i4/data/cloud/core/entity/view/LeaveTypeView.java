package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.LeaveTypeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-01 14:58:27
 */
public class LeaveTypeView extends BaseView<LeaveTypeView> {

	private static final long serialVersionUID = 1604213907801L;

	public LeaveTypeView(LeaveTypeModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.createTime = model.getCreateTime();
		this.userId = model.getUserId();
	}

	public LeaveTypeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;
	private String createTimeStr;

	/**
	 * 创建用户
	 */
	@TableField("user_id")
	private Integer userId;
	/**
	 * 创建用户的真实姓名
	 */
	private String realName;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
