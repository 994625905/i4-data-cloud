package cn.i4.data.cloud.base.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解：请求权限控制
 * @author wangjc
 * @title: RequestPermission
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/9-9:17
 */
@Target({ElementType.METHOD}) // 只作用于方法
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestPermission {

    /**
     * 权限的标识符
     * @return
     */
    String value() default "";

}
