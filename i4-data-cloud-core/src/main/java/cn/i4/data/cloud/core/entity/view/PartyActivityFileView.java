package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.PartyActivityFileModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-14 15:39:24
 */
public class PartyActivityFileView extends BaseView<PartyActivityFileView> {

	private static final long serialVersionUID = 1605339564801L;

	public PartyActivityFileView(PartyActivityFileModel model) {
		this.id = model.getId();
		this.activityId = model.getActivityId();
		this.createUserId = model.getCreateUserId();
		this.name = model.getName();
		this.url = model.getUrl();
		this.suffix = model.getSuffix();
		this.type = model.getType();
		this.size = model.getSize();
		this.createTime = model.getCreateTime();
	}

	public PartyActivityFileView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 活动id
	 */
	@TableField("activity_id")
	private Integer activityId;

	/**
	 * 创建用户
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 在线链接
	 */
	@TableField("url")
	private String url;

	/**
	 * 后缀类型
	 */
	@TableField("suffix")
	private String suffix;

	/**
	 * 1图片 2音频 3视频 4文档 5其他
	 */
	@TableField("type")
	private Integer type;

	/**
	 * 文件大小(单位：KB，上传有大小限制)
	 */
	@TableField("size")
	private Float size;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Long createTime;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getActivityId() {
		return this.activityId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public Float getSize() {
		return this.size;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

}
