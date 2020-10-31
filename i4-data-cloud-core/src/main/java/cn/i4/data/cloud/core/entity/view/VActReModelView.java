package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.VActReModelModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-30 16:52:07
 */
public class VActReModelView extends BaseView<VActReModelView> {

	private static final long serialVersionUID = 1604047927179L;

	public VActReModelView(VActReModelModel model) {
		this.modelId = model.getModelId();
		this.modelName = model.getModelName();
		this.modelKey = model.getModelKey();
		this.createTime = model.getCreateTime();
		this.lastUpdateTime = model.getLastUpdateTime();
		this.version = model.getVersion();
		this.metaInfo = model.getMetaInfo();
	}

	public VActReModelView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("model_id")
	private String modelId;

	/**
	 * 
	 */
	@TableField("model_name")
	private String modelName;

	/**
	 * 
	 */
	@TableField("model_key")
	private String modelKey;

	/**
	 * 
	 */
	@TableField("create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 
	 */
	@TableField("last_update_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date lastUpdateTime;

	/**
	 * 
	 */
	@TableField("version")
	private Integer version;

	/**
	 * 
	 */
	@TableField("meta_info")
	private String metaInfo;

	
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelId() {
		return this.modelId;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}

	public String getModelKey() {
		return this.modelKey;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}

	public String getMetaInfo() {
		return this.metaInfo;
	}

}
