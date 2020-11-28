package cn.i4.data.cloud.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class I4DataCloudStartTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(I4DataCloudStartTestApplication.class, args);
	}

}
