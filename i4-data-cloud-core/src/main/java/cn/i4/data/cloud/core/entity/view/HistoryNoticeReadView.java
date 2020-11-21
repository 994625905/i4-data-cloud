package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeReadModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-21 14:11:04
 */
public class HistoryNoticeReadView extends BaseView<HistoryNoticeReadView> {

	private static final long serialVersionUID = 1605939064227L;

	public HistoryNoticeReadView(HistoryNoticeReadModel model) {
		this.id = model.getId();
		this.noticeId = model.getNoticeId();
		this.createUserId = model.getCreateUserId();
		this.createTime = model.getCreateTime();
	}

	public HistoryNoticeReadView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 公告id
	 */
	@TableField("notice_id")
	private Integer noticeId;

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

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public Integer getNoticeId() {
		return this.noticeId;
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
