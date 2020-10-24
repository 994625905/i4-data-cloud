package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.RoleModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-11 13:42:25
 */
public class RoleView extends BaseView<RoleView> {

	private static final long serialVersionUID = 1602394945474L;

	public RoleView(RoleModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.describeInfo = model.getDescribeInfo();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
	}

	public RoleView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 描述
	 */
	@TableField("describe_info")
	private String describeInfo;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;
	private String createTimeStr;

	/**
	 * 
	 */
	@TableField("update_time")
	private Long updateTime;
	private String updateTimeStr;
	
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

	public String getDescribeInfo() {
		return describeInfo;
	}

	public void setDescribeInfo(String describeInfo) {
		this.describeInfo = describeInfo;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateTime() {
		return this.updateTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
}
