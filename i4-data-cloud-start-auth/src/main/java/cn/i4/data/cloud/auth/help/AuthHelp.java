package cn.i4.data.cloud.auth.help;

import cn.i4.data.cloud.core.entity.model.UserInfoModel;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.util.CookieUtil;
import cn.i4.data.cloud.base.util.JWTUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.cache.service.RedisService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjc
 * @title: AuthHelp
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1010:26
 */
public class AuthHelp {

    private static final Logger logger = LoggerFactory.getLogger(AuthHelp.class);

    /**
     * 登录检查
     * @param request
     * @param redisService
     * @return
     */
    public static UserModel loginCheck(HttpServletRequest request, RedisService redisService){
        String authorization = CookieUtil.getCookieValue(request,"authorization");

        if(StringUtil.isNullOrEmpty(authorization)){
            return null;
        }

        DecodedJWT decode = JWT.decode(authorization);
        UserModel userModel = redisService.get(RedisConstant.KEY.LOGIN_USER_PREFIX + decode.getClaim("userId").asInt(), UserModel.class);
        if(userModel != null){
            redisService.expire(RedisConstant.KEY.LOGIN_USER_PREFIX + decode.getClaim("userId").asInt(),RedisConstant.TIMEOUT.LOGIN_USER);//刷新时间
            redisService.expire(RedisConstant.KEY.LOGIN_USER_INFO_PREFIX + decode.getClaim("userId").asInt(),RedisConstant.TIMEOUT.LOGIN_USER_INFO);//刷新时间
            return userModel;
        }
        logger.debug("redis已过期，需重新登录。");
        return null;
    }

    /**
     * 登录,设置token到cookie,user到redis,返回token
     * @param userModel
     * @param redisService
     * @param response
     * @return
     */
    public static String login(UserModel userModel, UserInfoModel userInfo, RedisService redisService, HttpServletResponse response){
        if(userModel != null){
            String authorization = JWTUtil.sign(userModel.getId(),userModel.getLoginname());
            CookieUtil.set(response,"authorization",authorization);
            redisService.set(RedisConstant.KEY.LOGIN_USER_PREFIX + userModel.getId(),userModel,RedisConstant.TIMEOUT.LOGIN_USER);
            redisService.set(RedisConstant.KEY.LOGIN_USER_INFO_PREFIX + userModel.getId(),userInfo,RedisConstant.TIMEOUT.LOGIN_USER_INFO);
            return authorization;
        }
        return null;
    }

    /**
     * 注销登录
     * @param authorization
     * @param redisService
     */
    public static Boolean logout(String authorization,RedisService redisService){
        DecodedJWT decode = JWT.decode(authorization);
        Boolean del1 = redisService.del(RedisConstant.KEY.LOGIN_USER_PREFIX + decode.getClaim("userId").asInt());
        Boolean del2 = redisService.del(RedisConstant.KEY.LOGIN_USER_INFO_PREFIX + decode.getClaim("userId").asInt());
        return del1 && del2;
    }

}
