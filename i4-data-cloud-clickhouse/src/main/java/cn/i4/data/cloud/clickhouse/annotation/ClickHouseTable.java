package cn.i4.data.cloud.clickhouse.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * clickHouse表名注解，声明实体
 * @author wangjc
 * @title: ClickHouseTable
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2210:17
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickHouseTable {

    /**
     * 名称
     * @return
     */
    String name() default "";

}
