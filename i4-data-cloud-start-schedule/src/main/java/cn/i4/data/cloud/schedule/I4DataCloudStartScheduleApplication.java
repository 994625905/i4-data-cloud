package cn.i4.data.cloud.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * EnableDiscoveryClient和EnableEurekaClient效果差不多，都能将服务注册到注册中心，区别是前者可以注册到其他的注册中心，后者只能说eureka
 */
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.i4.data.cloud.*"})
public class I4DataCloudStartScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(I4DataCloudStartScheduleApplication.class, args);
	}

}
