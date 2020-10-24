package cn.i4.data.cloud.base.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，请求日志
 * @author wangjc
 * @title: RequestLog
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:34
 */
@Target({ElementType.METHOD}) // 只作用于方法
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLog {

    /**
     * 模块名称
     * @return
     */
    String module() default "";

    /**
     * 请求内容
     * @return
     */
    String content() default "";

    /**
     * 异常的简要信息
     * @return
     */
    String exception() default "";

    /**
     * 默认为查看,
     * 注解的成员变量只能使用基本类型、 String或者enum枚举，不可使用包装类型Integer……
     * @return
     */
    RequestType type() default RequestType.SELECT;


}
