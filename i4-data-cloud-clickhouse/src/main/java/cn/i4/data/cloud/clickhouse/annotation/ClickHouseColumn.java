package cn.i4.data.cloud.clickhouse.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明实体类中与clickHouse表字段的属性
 * @author wangjc
 * @title: ClickHouseColumn
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2210:18
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickHouseColumn {

    /**
     * 字段名
     * @return
     */
    String name() default "";

    /**
     * 表字段格式，一般不会用到（日期格式需要）
     * @return
     */
    String format() default "";

}
