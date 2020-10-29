package cn.i4.data.cloud.core.entity.view;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.SystemConstantModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-10-14 14:43:30
 */
public class SystemConstantView extends BaseView<SystemConstantView> {

	private static final long serialVersionUID = 1602657811089L;

	public SystemConstantView(SystemConstantModel model) {
		this.id = model.getId();
		this.constantName = model.getConstantName();
		this.constantKey = model.getConstantKey();
		this.constantValue = model.getConstantValue();
	}

	public SystemConstantView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 * 常量名称
	 */
	@TableField("constant_name")
	private String constantName;

	/**
	 * 常量key
	 */
	@TableField("constant_key")
	private String constantKey;

	/**
	 * 常量值
	 */
	@TableField("constant_value")
	private String constantValue;

	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setConstantName(String constantName) {
		this.constantName = constantName;
	}

	public String getConstantName() {
		return this.constantName;
	}

	public void setConstantKey(String constantKey) {
		this.constantKey = constantKey;
	}

	public String getConstantKey() {
		return this.constantKey;
	}

	public void setConstantValue(String constantValue) {
		this.constantValue = constantValue;
	}

	public String getConstantValue() {
		return this.constantValue;
	}

}
