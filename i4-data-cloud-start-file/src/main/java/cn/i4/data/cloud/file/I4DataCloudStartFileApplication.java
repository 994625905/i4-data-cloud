package cn.i4.data.cloud.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * EnableDiscoveryClient和EnableEurekaClient效果差不多，都能将服务注册到注册中心，区别是前者可以注册到其他的注册中心，后者只能说eureka
 */
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.i4.data.cloud.*"})
public class I4DataCloudStartFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(I4DataCloudStartFileApplication.class, args);
    }

}
