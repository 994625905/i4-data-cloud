package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AttendanceModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-23 13:55:44
 */
public class AttendanceView extends BaseView<AttendanceView> {

	private static final long serialVersionUID = 1606110944598L;

	public AttendanceView(AttendanceModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.createTime = model.getCreateTime();
		this.createDate = model.getCreateDate();
	}

	public AttendanceView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 打卡用户
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 打卡时间
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 打卡日期
	 */
	@TableField("create_date")
	private String createDate;

	/** 补充字段 */
	private String createTimeStr;
	private String userName;
	
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

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
