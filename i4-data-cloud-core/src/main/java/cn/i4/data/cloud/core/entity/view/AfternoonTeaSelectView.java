package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaSelectModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-10 19:54:04
 */
public class AfternoonTeaSelectView extends BaseView<AfternoonTeaSelectView> {

	private static final long serialVersionUID = 1605009244125L;

	public AfternoonTeaSelectView(AfternoonTeaSelectModel model) {
		this.id = model.getId();
		this.teaTaskId = model.getTeaTaskId();
		this.teaMenuId = model.getTeaMenuId();
		this.teaId = model.getTeaId();
		this.createTime = model.getCreateTime();
		this.selectUserId = model.getSelectUserId();
	}

	public AfternoonTeaSelectView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 下午茶任务id
	 */
	@TableField("tea_task_id")
	private Integer teaTaskId;

	/**
	 * 下午茶点单Id
	 */
	@TableField("tea_menu_id")
	private Integer teaMenuId;

	/**
	 * 下午茶id
	 */
	@TableField("tea_id")
	private Integer teaId;

	/**
	 * 点单时间
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 点单用户
	 */
	@TableField("select_user_id")
	private Integer selectUserId;

	private String createTimeStr;
	private String realName;
	private String teaName;
	private String teaImage;
	private String dateWeek;
	private String taskTitle;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTeaTaskId(Integer teaTaskId) {
		this.teaTaskId = teaTaskId;
	}

	public Integer getTeaTaskId() {
		return this.teaTaskId;
	}

	public void setTeaMenuId(Integer teaMenuId) {
		this.teaMenuId = teaMenuId;
	}

	public Integer getTeaMenuId() {
		return this.teaMenuId;
	}

	public void setTeaId(Integer teaId) {
		this.teaId = teaId;
	}

	public Integer getTeaId() {
		return this.teaId;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setSelectUserId(Integer selectUserId) {
		this.selectUserId = selectUserId;
	}

	public Integer getSelectUserId() {
		return this.selectUserId;
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

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getTeaImage() {
		return teaImage;
	}

	public void setTeaImage(String teaImage) {
		this.teaImage = teaImage;
	}

	public String getDateWeek() {
		return dateWeek;
	}

	public void setDateWeek(String dateWeek) {
		this.dateWeek = dateWeek;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
}
