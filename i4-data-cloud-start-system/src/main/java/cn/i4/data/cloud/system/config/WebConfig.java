package cn.i4.data.cloud.system.config;

import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.system.interceptors.SystemInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private RedisService redisService;

    /**
     * 统一认证中心
     */
    @Value("${i4.data.cloud.auth-url}")
    private String loginURL;
    /**
     * 重定向的登录地址
     */
    @Value("${i4.data.cloud.server-url}")
    private String redirectServer;
    /**
     * 默认页
     */
    @Value("${i4.data.cloud.main-page}")
    private String mainPage;

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
        registry.addViewController("/").setViewName(mainPage);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SystemInterceptor(this.redisService,this.loginURL,this.redirectServer)).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
