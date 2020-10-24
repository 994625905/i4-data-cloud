package cn.i4.data.cloud.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring bean的工具类
 * @author wangjc
 * @title: SpringBeanUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3015:52
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static <T> T getBean(String beanName,Class<T> clazz){
        return applicationContext.getBean(beanName,clazz);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
