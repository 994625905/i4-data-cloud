package cn.i4.data.cloud.base.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，请求接口限流
 * @author wangjc
 * @title: RequestLimit
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/28-18:05
 */
@Target({ElementType.METHOD}) // 只作用于方法
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLimit {

    /**
     * 限流接口的名称
     * @return
     */
    String name() default  "";

    /**
     * 定向限流的key（组成部分）
     * @return
     */
    String key() default "";

    /**
     * 定向接口限流的前缀
     * @return
     */
    String prefix() default "limit";

    /**
     * 时间范围，单位秒
     * @return
     */
    int period() default 2;

    /**
     * 限制访问次数，（默认2秒钟之内只能访问1次，具体到IP/用户/接口）
     * @return
     */
    int count() default 1;

    /**
     * 限流类型，默认为用户身份限流
     * @return
     */
    LimitType type() default LimitType.USER;


}
