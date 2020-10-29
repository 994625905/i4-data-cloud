package cn.i4.data.cloud.activiti.design;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class I4DataCloudStartActivitiDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(I4DataCloudStartActivitiDesignApplication.class, args);
	}

}
