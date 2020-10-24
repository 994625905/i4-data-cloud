package cn.i4.data.cloud.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;

/**
 * 全局的跨域配置
 * @author wangjc
 * @title: CorsConfiguration
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/3010:52
 */
@Configuration
public class CorsConfiguration implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 同源判断
        if(!CorsUtils.isCorsRequest(request)){
            return chain.filter(exchange);
        }else{
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders requestHeaders = request.getHeaders();
            HttpHeaders responseHeaders = response.getHeaders();
            HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();

            responseHeaders.add("Access-Control-Allow-Origin", requestHeaders.getOrigin());

            List<String> accessControlRequestHeadersArray = requestHeaders.getAccessControlRequestHeaders();
            String accessControlRequestHeadersStr = "";
            String str;

            if (accessControlRequestHeadersArray != null) {
                for(Iterator var9 = accessControlRequestHeadersArray.iterator(); var9.hasNext(); accessControlRequestHeadersStr = accessControlRequestHeadersStr + str) {
                    str = (String)var9.next();
                    if (!"".equals(accessControlRequestHeadersStr)) {
                        accessControlRequestHeadersStr = accessControlRequestHeadersStr + ", ";
                    }
                }
            }
            responseHeaders.add("Access-Control-Allow-Headers", accessControlRequestHeadersStr);
            responseHeaders.addAll("Access-Control-Allow-Headers", accessControlRequestHeadersArray);
            responseHeaders.add("Access-Control-Request-Headers", accessControlRequestHeadersStr);
            responseHeaders.addAll("Access-Control-Request-Headers", accessControlRequestHeadersArray);
            if (requestMethod != null) {
                responseHeaders.add("Access-Control-Allow-Methods", requestMethod.name());
            }
            responseHeaders.add("Access-Control-Allow-Credentials", "true");
            responseHeaders.add("Access-Control-Expose-Headers", "*");
            responseHeaders.add("Access-Control-Max-Age", "18000L");
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            } else {
                return chain.filter(exchange);
            }
        }
    }
}
