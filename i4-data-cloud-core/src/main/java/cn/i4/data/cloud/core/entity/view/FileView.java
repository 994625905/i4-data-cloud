package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-13 11:39:37
 */
public class FileView extends BaseView<FileView> {

	private static final long serialVersionUID = 1602560378509L;

	public FileView(FileModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.cover = model.getCover();
		this.url = model.getUrl();
		this.suffix = model.getSuffix();
		this.type = model.getType();
		this.size = model.getSize();
		this.width = model.getWidth();
		this.height = model.getHeight();
		this.status = model.getStatus();
		this.description = model.getDescription();
		this.createTime = model.getCreateTime();
		this.createUserId = model.getCreateUserId();
	}

	public FileView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 文件名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 封面，一般用于展示的信息
	 */
	@TableField("cover")
	private String cover;

	/**
	 * 在线链接
	 */
	@TableField("url")
	private String url;

	/**
	 * 后缀
	 */
	@TableField("suffix")
	private String suffix;

	/**
	 * 文件类型: 1图片 2音频 3视频 4文档 5其他
	 */
	@TableField("type")
	private Integer type;

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
	 * 1正常，0禁用
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 描述
	 */
	@TableField("description")
	private String description;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Long createTime;
	private String createTimeStr;

	/**
	 * 创建用户
	 */
	@TableField("create_user_id")
	private Integer createUserId;
	private String realname;
	
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

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCover() {
		return this.cover;
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

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
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

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
}
