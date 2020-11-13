package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTypeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-13 13:51:17
 */
public class AfternoonTeaTypeView extends BaseView<AfternoonTeaTypeView> {

	private static final long serialVersionUID = 1605246677836L;

	public AfternoonTeaTypeView(AfternoonTeaTypeModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.createTime = model.getCreateTime();
		this.createUserId = model.getCreateUserId();
	}

	public AfternoonTeaTypeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 类型名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 创建者
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	private String createTimeStr;
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

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
