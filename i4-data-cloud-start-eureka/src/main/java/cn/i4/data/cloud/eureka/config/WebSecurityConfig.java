package cn.i4.data.cloud.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 身份认证配置
 * @author wangjc
 * @title: WebSecurityConfig
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2918:55
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 默认情况下添加SpringSecurity依赖的应用每个请求都需要添加CSRF token才能访问，
     * Eureka客户端注册时并不会添加，所以需要配置/eureka/**路径不需要CSRF token。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
