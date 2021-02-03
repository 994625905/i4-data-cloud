package cn.i4.data.cloud.system.interceptors;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.util.CookieUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.entity.model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http请求拦截器
 * @author wangjc
 * @title: SystemInterceptor
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1017:35
 */
public class SystemInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SystemInterceptor.class);

    private RedisService redisService;
    private String loginURL;
    private String redirectServer;

    public SystemInterceptor(RedisService redisService,String loginURL,String redirectServer){
        this.redisService = redisService;
        this.loginURL = loginURL;
        this.redirectServer = redirectServer;
    }

    /**
     * 请求执行前动作，判断是否认证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        String authorization = CookieUtil.getCookieValue(request, "authorization");
        if(StringUtil.isNullOrEmpty(authorization)){
            response.sendRedirect(this.loginURL+"?redirect="+this.redirectServer);
            return false;
        }

        DecodedJWT decode = JWT.decode(authorization);
        UserModel userModel = redisService.get(RedisConstant.KEY.LOGIN_USER_PREFIX + decode.getClaim("userId").asInt(), UserModel.class);
        if(userModel == null){
            response.sendRedirect(this.loginURL+"?redirect="+this.redirectServer);
            return false;
        }

        CookieUtil.set(response,"authorization",authorization);//刷新时间
        redisService.expire(RedisConstant.KEY.LOGIN_USER_PREFIX + decode.getClaim("userId").asInt(),RedisConstant.TIMEOUT.LOGIN_USER);//刷新时间
        redisService.expire(RedisConstant.KEY.LOGIN_USER_INFO_PREFIX + decode.getClaim("userId").asInt(),RedisConstant.TIMEOUT.LOGIN_USER_INFO);//刷新时间
        redisService.expire(RedisConstant.KEY.LOGIN_USER_ROLE_MENU_TREE_PREFIX + decode.getClaim("userId").asInt(),RedisConstant.TIMEOUT.LOGIN_USER_ROLE_MENU_TREE);//刷新时间
        return true;
    }

    /**
     * 请求结束后动作（preHandle必须返回true）
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    /**
     * 视图渲染后动作（preHandle必须返回true）
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
