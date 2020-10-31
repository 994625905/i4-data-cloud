package cn.i4.data.cloud.activiti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * EnableDiscoveryClient和EnableEurekaClient效果差不多，都能将服务注册到注册中心，区别是前者可以注册到其他的注册中心，后者只能说eureka
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = SecurityAutoConfiguration.class,scanBasePackages = {"cn.i4.data.cloud.*"})
public class I4DataCloudStartActivitiDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(I4DataCloudStartActivitiDesignApplication.class, args);
	}

}
