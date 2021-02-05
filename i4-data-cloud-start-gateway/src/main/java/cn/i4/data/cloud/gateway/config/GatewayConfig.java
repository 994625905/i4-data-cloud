package cn.i4.data.cloud.gateway.config;

import cn.i4.data.cloud.gateway.filter.AuthorizeFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjc
 * @title: GateWayConfig
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/12/7-16:48
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes().route(predicateSpec ->
//                predicateSpec.path("/i4-data-cloud-start-system/autoCode/codeLog/**")
//                        .filters(gatewayFilterSpec -> gatewayFilterSpec.stripPrefix(1).filter(new AuthorizeFilter()))
//                        .uri("http://localhost:9011/").id("system")
//        ).build();

        return builder.routes()
                .route(r -> r.path("/i4-data-cloud-start-system/autoCode/codeLog/**")
                        .filters(f -> f.filter(new AuthorizeFilter()))
                        .uri("http://localhost:9011")
                        .order(0)
                        .id("system")
                ).build();
    }

}
