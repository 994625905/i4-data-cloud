package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageReadModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-18 10:11:37
 */
public class PartyActivityImageReadView extends BaseView<PartyActivityImageReadView> {

	private static final long serialVersionUID = 1605665497308L;

	public PartyActivityImageReadView(PartyActivityImageReadModel model) {
		this.id = model.getId();
		this.imageId = model.getImageId();
		this.createUserId = model.getCreateUserId();
		this.createTime = model.getCreateTime();
	}

	public PartyActivityImageReadView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 
	 */
	@TableField("image_id")
	private Integer imageId;

	/**
	 * 
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	/**
	 * 
	 */
	@TableField("create_time")
	private Long createTime;

	
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

}
