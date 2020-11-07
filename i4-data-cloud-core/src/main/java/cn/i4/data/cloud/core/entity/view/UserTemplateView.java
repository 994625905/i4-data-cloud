package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.UserTemplateModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-06 15:24:53
 */
public class UserTemplateView extends BaseView<UserTemplateView> {

	private static final long serialVersionUID = 1604647493146L;

	public UserTemplateView(UserTemplateModel model) {
		this.id = model.getId();
		this.userId = model.getUserId();
		this.weekreportTitle = model.getWeekreportTitle();
	}

	public UserTemplateView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 用户id
	 */
	@TableField("user_id")
	private Integer userId;

	/**
	 * 周报标题模板
	 */
	@TableField("weekreport_title")
	private String weekreportTitle;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setWeekreportTitle(String weekreportTitle) {
		this.weekreportTitle = weekreportTitle;
	}

	public String getWeekreportTitle() {
		return this.weekreportTitle;
	}

}
