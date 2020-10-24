package cn.i4.data.cloud.auth.config;

import cn.i4.data.cloud.auth.filter.AuthLoginFilter;
import cn.i4.data.cloud.auth.interceptors.AuthLoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web层核心配置
 * @author wangjc
 * @title: WebConfig
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/109:51
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/",
                "classpath:/resources/", "classpath:/static/", "classpath:/resource/", "classpath:/", "classpath*:*");
    }

    /**
     * 默认打开页面
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/auth/page/index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthLoginInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registrationFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthLoginFilter());
        registration.addUrlPatterns("/*");//添加过滤规则
        registration.setName("ReportFilter");
        registration.setOrder(1);//优先级
        return registration;
    }

}
