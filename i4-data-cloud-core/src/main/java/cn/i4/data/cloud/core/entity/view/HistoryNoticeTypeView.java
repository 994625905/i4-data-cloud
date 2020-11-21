package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeTypeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-21 14:11:04
 */
public class HistoryNoticeTypeView extends BaseView<HistoryNoticeTypeView> {

	private static final long serialVersionUID = 1605939064245L;

	public HistoryNoticeTypeView(HistoryNoticeTypeModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.describeInfo = model.getDescribeInfo();
	}

	public HistoryNoticeTypeView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 公告名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 描述信息
	 */
	@TableField("describe_info")
	private String describeInfo;

	
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

	public void setDescribeInfo(String describeInfo) {
		this.describeInfo = describeInfo;
	}

	public String getDescribeInfo() {
		return this.describeInfo;
	}

}
