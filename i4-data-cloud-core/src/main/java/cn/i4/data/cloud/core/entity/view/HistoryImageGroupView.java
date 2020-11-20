package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.HistoryImageGroupModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-19 19:47:04
 */
public class HistoryImageGroupView extends BaseView<HistoryImageGroupView> {

	private static final long serialVersionUID = 1605786424886L;

	public HistoryImageGroupView(HistoryImageGroupModel model) {
		this.id = model.getId();
		this.createUserId = model.getCreateUserId();
		this.name = model.getName();
		this.describeInfo = model.getDescribeInfo();
		this.createTime = model.getCreateTime();
	}

	public HistoryImageGroupView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 创建者
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 描述信息
	 */
	@TableField("describe_info")
	private String describeInfo;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;

	/** 补充字段 */
	private String createUserName;
	private String imageCover;
	private Integer imageCount;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
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

	public void setDescribeInfo(String describeInfo) {
		this.describeInfo = describeInfo;
	}

	public String getDescribeInfo() {
		return this.describeInfo;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getImageCover() {
		return imageCover;
	}

	public void setImageCover(String imageCover) {
		this.imageCover = imageCover;
	}

	public Integer getImageCount() {
		return imageCount;
	}

	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}
}
