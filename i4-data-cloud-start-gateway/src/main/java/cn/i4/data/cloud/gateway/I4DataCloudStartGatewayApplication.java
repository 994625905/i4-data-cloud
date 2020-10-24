package cn.i4.data.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.i4.data.cloud.*"})
public class I4DataCloudStartGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(I4DataCloudStartGatewayApplication.class, args);
    }

}
