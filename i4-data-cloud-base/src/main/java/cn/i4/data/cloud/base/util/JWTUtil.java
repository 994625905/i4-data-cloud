package cn.i4.data.cloud.base.util;

import cn.hutool.core.convert.Convert;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * token生成和解析的工具
 * @author wangjc
 * @title: JWTUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:50
 */
public class JWTUtil {

    private final static Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    private final static String USER_ID = "userId";
    private final static String LOGIN_NAME = "loginName";

    /**
     * 解析userId
     * @param request
     * @param header
     * @return
     */
    public static Integer getUserId(HttpServletRequest request,String header){
        return Convert.toInt(getTokenValueByRequestAndKey(request,USER_ID,header));
    }

    public static Integer getUserId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getUserId(request,"authorization");
    }

    /**
     * 解析PHONE
     * @param request
     * @param header
     * @return
     */
    public static String getUserName(HttpServletRequest request, String header){
        return getTokenValueByRequestAndKey(request,LOGIN_NAME,header);
    }

    public static String getUserName(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getUserName(request,"authorization");
    }

    /**
     * 根据key值解析token
     * @param request
     * @param key
     * @param header
     * @return
     */
    private static String getTokenValueByRequestAndKey(HttpServletRequest request, String key, String header){
        String token = request.getHeader(header);
        if (!StringUtil.isNullOrEmpty(token)) {
            try {
                DecodedJWT jwt = JWT.decode(token);
                Claim claim = jwt.getClaim(key);
                return claim.asString() != null ? claim.asString() : claim.asInt().toString();
            } catch (JWTDecodeException e) {
                logger.error("token解析失败");
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 用户ID+PHONE生成token
     * @param userId
     * @param loginName
     * @return
     */
    public static String sign(Integer userId, String loginName){
        try {
            Algorithm algorithm = Algorithm.HMAC256(userId.toString());
            String sign = JWT.create().withClaim(LOGIN_NAME, loginName).withClaim(USER_ID, userId).sign(algorithm);
            return sign;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("token生成失败");
        }
        return null;
    }

}
