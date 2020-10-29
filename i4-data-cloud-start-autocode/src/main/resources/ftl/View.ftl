package cn.i4.data.cloud.${ModuleName}.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.${ModuleName}.entity.model.${ModelName}Model;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ${table_Comment}View
 * @author ${author}
 * @date ${crateDate}
 */
public class ${ModelName}View extends BaseView<${ModelName}View> {

	private static final long serialVersionUID = ${serialVersionUID};

	public ${ModelName}View(${ModelName}Model model) {
		<#list fields.fields as field>
		this.<@linetohump value="${field.fieldName}" /> = model.<@autoget value="${field.fieldName}" />();
		</#list>
	}

	public ${ModelName}View() {
		
	}
	
	<#list fields.fields as field>
	/**
	 * ${field.fieldComment}
	 */
	@TableField("${field.fieldName}")
	<#if field.filed.javaType == 'java.util.Date'>
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	</#if>
	private ${field.filed.javaType} <@linetohump value="${field.fieldName}" />;

	</#list>
	
	<#list fields.fields as field>
	public void <@autoset value="${field.fieldName}" />(${field.filed.javaType} <@linetohump value="${field.fieldName}" />) {
		this.<@linetohump value="${field.fieldName}" /> = <@linetohump value="${field.fieldName}" />;
	}

	public ${field.filed.javaType} <@autoget value="${field.fieldName}" />() {
		return this.<@linetohump value="${field.fieldName}" />;
	}

	</#list>
}
