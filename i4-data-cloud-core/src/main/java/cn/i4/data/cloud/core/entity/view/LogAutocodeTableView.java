package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.LogAutocodeTableModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-25 17:37:12
 */
public class LogAutocodeTableView extends BaseView<LogAutocodeTableView> {

	private static final long serialVersionUID = 1603618632572L;

	public LogAutocodeTableView(LogAutocodeTableModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.loginName = model.getLoginName();
		this.autocodeLogId = model.getAutocodeLogId();
		this.tableName = model.getTableName();
		this.createTime = model.getCreateTime();
		this.createResult = model.getCreateResult();
	}

	public LogAutocodeTableView() {
		
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
	@TableField("login_name")
	private String loginName;

	/**
	 * 生成日志ID
	 */
	@TableField("autocode_log_id")
	private Integer autocodeLogId;

	/**
	 * 
	 */
	@TableField("table_name")
	private String tableName;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;
	private String createTimeStr;

	/**
	 * 0成功，1失败
	 */
	@TableField("create_result")
	private Integer createResult;

	
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

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setAutocodeLogId(Integer autocodeLogId) {
		this.autocodeLogId = autocodeLogId;
	}

	public Integer getAutocodeLogId() {
		return this.autocodeLogId;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return this.tableName;
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

	public void setCreateResult(Integer createResult) {
		this.createResult = createResult;
	}

	public Integer getCreateResult() {
		return this.createResult;
	}

}
