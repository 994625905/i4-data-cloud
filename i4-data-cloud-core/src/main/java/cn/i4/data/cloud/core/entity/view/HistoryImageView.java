package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.HistoryImageModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-19 19:47:04
 */
public class HistoryImageView extends BaseView<HistoryImageView> {

	private static final long serialVersionUID = 1605786424862L;

	public HistoryImageView(HistoryImageModel model) {
		this.id = model.getId();
		this.groupId = model.getGroupId();
		this.name = model.getName();
		this.url = model.getUrl();
		this.suffix = model.getSuffix();
		this.size = model.getSize();
		this.width = model.getWidth();
		this.height = model.getHeight();
		this.createUserId = model.getCreateUserId();
		this.createTime = model.getCreateTime();
	}

	public HistoryImageView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 照片组id
	 */
	@TableField("group_id")
	private Integer groupId;

	/**
	 * 照片名称
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
	 * 文件大小(单位：KB，上传有大小限制)
	 */
	@TableField("size")
	private Float size;

	/**
	 * 图片宽度
	 */
	@TableField("width")
	private Integer width;

	/**
	 * 图片高度
	 */
	@TableField("height")
	private Integer height;

	/**
	 * 创建人
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;
	private String createTimeStr;

	/** 补充字段，上传者，点赞数，阅读数，当前用户是否点赞（0，1） */
	private String createUserName;
	private Integer likeCount;
	private Integer readCount;
	private Integer isLike;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getGroupId() {
		return this.groupId;
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

	public void setSize(Float size) {
		this.size = size;
	}

	public Float getSize() {
		return this.size;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
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

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}
}
