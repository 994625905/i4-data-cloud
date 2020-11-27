package cn.i4.data.cloud.clickhouse.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明实体类中与表id对应的属性
 * @author wangjc
 * @title: ClickHouseID
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2216:00
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickHouseID {

}
