package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AttendanceCalendarModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-24 20:43:55
 */
public class AttendanceCalendarView extends BaseView<AttendanceCalendarView> {

	private static final long serialVersionUID = 1606221835432L;

	public AttendanceCalendarView(AttendanceCalendarModel model) {
		this.id = model.getId();
		this.year = model.getYear();
		this.month = model.getMonth();
		this.week = model.getWeek();
		this.holidayName = model.getHolidayName();
		this.date = model.getDate();
		this.type = model.getType();
		this.createTime = model.getCreateTime();
		this.createUserId = model.getCreateUserId();
	}

	public AttendanceCalendarView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 具体年份
	 */
	@TableField("year")
	private Integer year;

	/**
	 * 月
	 */
	@TableField("month")
	private Integer month;

	/**
	 * 周几
	 */
	@TableField("week")
	private String week;

	/**
	 * 假日名称，type=2有效
	 */
	@TableField("holiday_name")
	private String holidayName;

	/**
	 * 日期，yyyyMMDD
	 */
	@TableField("date")
	private String date;

	/**
	 * 2法定节假日，3法定工作日
	 */
	@TableField("type")
	private Integer type;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	private String createTimeStr;
	private String createUserName;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getWeek() {
		return this.week;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayName() {
		return this.holidayName;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return this.date;
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

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}
