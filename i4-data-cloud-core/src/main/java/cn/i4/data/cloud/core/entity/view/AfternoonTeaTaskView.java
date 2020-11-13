package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-10 19:54:04
 */
public class AfternoonTeaTaskView extends BaseView<AfternoonTeaTaskView> {

	private static final long serialVersionUID = 1605009244096L;

	public AfternoonTeaTaskView(AfternoonTeaTaskModel model) {
		this.id = model.getId();
		this.title = model.getTitle();
		this.endTime = model.getEndTime();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
		this.createUserId = model.getCreateUserId();
		this.status = model.getStatus();
		this.describeInfo = model.getDescribeInfo();
	}

	public AfternoonTeaTaskView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 标题
	 */
	@TableField("title")
	private String title;

	/**
	 * 结束时间，过期无法进入
	 */
	@TableField("end_time")
	private Long endTime;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 修改时间
	 */
	@TableField("update_time")
	private Long updateTime;

	/**
	 * 创建时间
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 0未发布，1已发布，2已过期
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 描述信息
	 */
	@TableField("describe_info")
	private String describeInfo;

	private String endTimeStr;
	private String createTimeStr;
	private String updateTimeStr;
	private String realName;
	private Integer menuCount;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getEndTime() {
		return this.endTime;
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

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public String getDescribeInfo() {
		return describeInfo;
	}

	public void setDescribeInfo(String describeInfo) {
		this.describeInfo = describeInfo;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getMenuCount() {
		return menuCount;
	}

	public void setMenuCount(Integer menuCount) {
		this.menuCount = menuCount;
	}
}
