package cn.i4.data.cloud.${ModuleName}.entity.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.i4.data.cloud.base.entity.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* ${table_Comment}Model
* @author ${author}
* @date ${crateDate}
*/
@TableName("${table_name}")
public class ${ModelName}Model extends BaseModel<${ModelName}Model> {

    private static final long serialVersionUID = ${serialVersionUID};
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
