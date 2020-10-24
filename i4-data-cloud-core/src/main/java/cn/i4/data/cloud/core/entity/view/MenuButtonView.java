package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.MenuButtonModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-11 13:42:25
 */
public class MenuButtonView extends BaseView<MenuButtonView> {

	private static final long serialVersionUID = 1602394945635L;

	public MenuButtonView(MenuButtonModel model) {
		this.id = model.getId();
		this.parentId = model.getParentId();
		this.name = model.getName();
		this.icon = model.getIcon();
		this.url = model.getUrl();
		this.permission = model.getPermission();
		this.sort = model.getSort();
		this.type = model.getType();
		this.status = model.getStatus();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
	}

	public MenuButtonView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 上级id
	 */
	@TableField("parent_id")
	private Integer parentId;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 
	 */
	@TableField("url")
	private String url;

	/**
	 * 权限标识
	 */
	@TableField("permission")
	private String permission;

	/**
	 * 排序
	 */
	@TableField("sort")
	private Integer sort;

	/**
	 * 0菜单，1按钮
	 */
	@TableField("type")
	private Integer type;

	/**
	 * 状态，0冻结，1启用
	 */
	@TableField("status")
	private Integer status;

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

	/**
	 * 子节点，递归填充
	 */
	private List<MenuButtonView> child;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
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

	public List<MenuButtonView> getChild() {
		return child;
	}

	public void setChild(List<MenuButtonView> child) {
		this.child = child;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
