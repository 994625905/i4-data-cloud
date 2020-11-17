package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.PartyActivityTypeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-14 14:20:33
 */
public class PartyActivityTypeView extends BaseView<PartyActivityTypeView> {

	private static final long serialVersionUID = 1605334833925L;

	public PartyActivityTypeView(PartyActivityTypeModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.createTime = model.getCreateTime();
		this.createUserId = model.getCreateUserId();
	}

	public PartyActivityTypeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 类型名称，包含但不限于年会，周年庆，出游……
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 创建人
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	private String realName;
	private String createTimeStr;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}
