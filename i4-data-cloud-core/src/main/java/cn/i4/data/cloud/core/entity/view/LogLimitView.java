package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.LogLimitModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-28 19:40:49
 */
public class LogLimitView extends BaseView<LogLimitView> {

	private static final long serialVersionUID = 1603885249706L;

	public LogLimitView(LogLimitModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.period = model.getPeriod();
		this.count = model.getCount();
		this.prefix = model.getPrefix();
		this.limitKey = model.getLimitKey();
		this.requestPath = model.getRequestPath();
		this.type = model.getType();
		this.typeContent = model.getTypeContent();
		this.createTime = model.getCreateTime();
	}

	public LogLimitView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 限流名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 时间范围，单位秒
	 */
	@TableField("period")
	private Integer period;

	/**
	 * 限制访问次数
	 */
	@TableField("count")
	private Integer count;

	/**
	 * 定向接口限流的前缀
	 */
	@TableField("prefix")
	private String prefix;

	/**
	 * 定向限流的key
	 */
	@TableField("limit_key")
	private String limitKey;

	/**
	 * 请求路径
	 */
	@TableField("request_path")
	private String requestPath;

	/**
	 * 0:IP限制，1用户限制，2普通限制
	 */
	@TableField("type")
	private Integer type;

	/**
	 * 类型内容，IP或者loginName
	 */
	@TableField("type_content")
	private String typeContent;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Long createTime;

	private String createTimeStr;
	
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

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getPeriod() {
		return this.period;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public String getLimitKey() {
		return limitKey;
	}

	public void setLimitKey(String limitKey) {
		this.limitKey = limitKey;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}

	public String getTypeContent() {
		return this.typeContent;
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
}
