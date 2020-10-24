package cn.i4.data.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class I4DataCloudStartEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(I4DataCloudStartEurekaApplication.class, args);
    }

}
