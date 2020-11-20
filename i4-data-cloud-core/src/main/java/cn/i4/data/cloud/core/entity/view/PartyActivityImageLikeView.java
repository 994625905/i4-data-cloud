package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageLikeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-18 10:11:37
 */
public class PartyActivityImageLikeView extends BaseView<PartyActivityImageLikeView> {

	private static final long serialVersionUID = 1605665497281L;

	public PartyActivityImageLikeView(PartyActivityImageLikeModel model) {
		this.id = model.getId();
		this.imageId = model.getImageId();
		this.createUserId = model.getCreateUserId();
		this.createTime = model.getCreateTime();
		this.type = model.getType();
	}

	public PartyActivityImageLikeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 照片id
	 */
	@TableField("image_id")
	private Integer imageId;

	/**
	 * 
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private Long createTime;

	/**
	 * 点赞类型：1点赞，-1取赞
	 */
	@TableField("type")
	private Integer type;
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Integer getImageId() {
		return this.imageId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
