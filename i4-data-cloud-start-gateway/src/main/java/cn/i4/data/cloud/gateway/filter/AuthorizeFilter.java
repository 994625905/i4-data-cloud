package cn.i4.data.cloud.gateway.filter;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.result.ResultEnum;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.cache.service.RedisService;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 认证过滤器，校验token值
 * @author wangjc
 * @title: AuthorizeFilter
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2920:36
 */
@Component
public class AuthorizeFilter implements GatewayFilter, Ordered {

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().value();

            // 放行获取token的接口
            if(path.startsWith("/auth")){
                return chain.filter(exchange);
            }
            // 获取请求头部携带的token
            String token = request.getHeaders().getFirst("token");//外部接口的token,相当于令牌key
            String authorization = request.getHeaders().getFirst("authorization");//web层的token

            if(StringUtil.isNullOrEmpty(token) && StringUtil.isNullOrEmpty(token)){
                return this.response(exchange,ActionResult.error(ResultEnum.UNAUTHORIZED.getCode(),ResultEnum.UNAUTHORIZED.getMsg()));
            }
            // 开放接口的token验证
            if(!StringUtil.isNullOrEmpty(token)){
                String verifyToken = redisService.get(RedisConstant.KEY.OPEN_API_PREFIX+path,String.class);
                if(!token.equals(verifyToken)){
                    return this.response(exchange,ActionResult.error(ResultEnum.UNAUTHORIZED.getCode(),ResultEnum.UNAUTHORIZED.getMsg()));
                }
            }
            // web层的token验证，顺便刷新过期时间
            if(!StringUtil.isNullOrEmpty(authorization)){
                DecodedJWT decode = JWT.decode(authorization);
                String user = redisService.get(RedisConstant.KEY.LOGIN_USER_PREFIX + decode.getClaim("userId").asInt(), String.class);
                if(StringUtil.isNullOrEmpty(user)){
                    return this.response(exchange,ActionResult.error(ResultEnum.UNAUTHORIZED.getCode(),ResultEnum.UNAUTHORIZED.getMsg()));
                }else{
                    redisService.expire(RedisConstant.KEY.LOGIN_USER_PREFIX + decode.getClaim("userId").asInt(),RedisConstant.TIMEOUT.LOGIN_USER);
                }
            }
            return chain.filter(exchange);
        }catch (Exception e){
            return this.response(exchange,ActionResult.error(ResultEnum.UNAUTHORIZED.getCode(),ResultEnum.UNAUTHORIZED.getMsg()));
        }
    }

    /**
     * 默认的回复
     * @param exchange
     * @param result
     * @return
     */
    private Mono<Void> response(ServerWebExchange exchange, ActionResult result){
        ServerHttpResponse response = exchange.getResponse();
        byte[] info = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);

        DataBuffer dataBuffer = response.bufferFactory().wrap(info);

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
