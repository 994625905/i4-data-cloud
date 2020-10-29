package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.LogAutocodeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-25 17:37:12
 */
public class LogAutocodeView extends BaseView<LogAutocodeView> {

	private static final long serialVersionUID = 1603618632510L;

	public LogAutocodeView(LogAutocodeModel model) {
		this.id = model.getId();
		this.datasourceId = model.getDatasourceId();
		this.createTime = model.getCreateTime();
		this.userId = model.getUserId();
		this.loginName = model.getLoginName();
		this.createAuthor = model.getCreateAuthor();
		this.createPackagePrefix = model.getCreatePackagePrefix();
		this.createLocal = model.getCreateLocal();
		this.createResult = model.getCreateResult();
	}

	public LogAutocodeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("datasource_id")
	private Integer datasourceId;

	/**
	 * 生成时间
	 */
	@TableField("create_time")
	private Long createTime;
	private String createTimeStr;

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
	 * 作者
	 */
	@TableField("create_author")
	private String createAuthor;

	/**
	 * 包前缀
	 */
	@TableField("create_package_prefix")
	private String createPackagePrefix;

	/**
	 * 保存路径
	 */
	@TableField("create_local")
	private String createLocal;

	/**
	 * 1成功。0失败
	 */
	@TableField("create_result")
	private Integer createResult;

	/**
	 * 数据源名称
	 */
	private String datasourceName;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setDatasourceId(Integer datasourceId) {
		this.datasourceId = datasourceId;
	}

	public Integer getDatasourceId() {
		return this.datasourceId;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
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

	public void setCreateAuthor(String createAuthor) {
		this.createAuthor = createAuthor;
	}

	public String getCreateAuthor() {
		return this.createAuthor;
	}

	public void setCreatePackagePrefix(String createPackagePrefix) {
		this.createPackagePrefix = createPackagePrefix;
	}

	public String getCreatePackagePrefix() {
		return this.createPackagePrefix;
	}

	public void setCreateLocal(String createLocal) {
		this.createLocal = createLocal;
	}

	public String getCreateLocal() {
		return this.createLocal;
	}

	public void setCreateResult(Integer createResult) {
		this.createResult = createResult;
	}

	public Integer getCreateResult() {
		return this.createResult;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}
}
