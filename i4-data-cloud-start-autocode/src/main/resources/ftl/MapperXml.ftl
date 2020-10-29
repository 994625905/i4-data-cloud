<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.i4.data.cloud.${ModuleName}.mapper.${ModelName}Mapper">

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
	<#list fields.fields as field>
        t.${field.fieldName} as <@linetohump value="${field.fieldName}"/><#if field_has_next>, </#if>
	</#list>
    </sql>

    <select id="selectPage" parameterType="cn.i4.data.cloud.${ModuleName}.entity.dto.${ModelName}Dto"
            resultType="cn.i4.data.cloud.${ModuleName}.entity.view.${ModelName}View">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${table_name} AS t

    </select>

</mapper>