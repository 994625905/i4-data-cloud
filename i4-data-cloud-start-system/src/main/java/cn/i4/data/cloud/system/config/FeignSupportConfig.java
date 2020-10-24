package cn.i4.data.cloud.system.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringCloud使用FeignClient跨服务上传文件需要额外注意（之前是不支持跨服务上传的）
 * 微服务的文件上传，需要在网关中同样配置上传的限制大小（默认）
 * @author wangjc
 * @title: FeignSupportConfig
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1319:26
 */
@Configuration
public class FeignSupportConfig {

    /**
     * 跨服务文件上传的支持
     * @return
     */
    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }

}
