package cn.i4.data.cloud.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * EnableDiscoveryClient和EnableEurekaClient效果差不多，都能将服务注册到注册中心，区别是前者可以注册到其他的注册中心，后者只能说eureka
 * 弃用springboot自动配置的mongoDB，采用自定义配置，避免冲突报错
 */
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"cn.i4.data.cloud.*"},exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class I4DataCloudStartSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(I4DataCloudStartSystemApplication.class, args);
    }

}
