package cn.i4.data.cloud.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * @author wangjc
 * @title: CookieUtil
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:56
 */
public class CookieUtil {

    /**
     * 默认缓存时间,单位/秒, 与redis同步，1天
     */
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 1;
    /**
     * 保存路径,根路径
     */
    private static final String COOKIE_PATH = "/";
    /**
     * 设置cookie
     * @param response
     * @param name，新建cookie的name
     * @param value,新建cookie的value
     * @param domain，跨域共享信任的站点
     * @param path，同一应用服务内共享
     * @param maxAge，如果设置为负值的话，则为浏览器进程Cookie(内存中保存)，关闭浏览器就失效；如果设置为0，则立即删除该Cookie。正值则为保存时间
     * @param isHttpOnly，增加对xss防护的安全系数，如果为true，只能通过HTTP请求，JavaScript无法请求到
     */
    private static void setCookie(HttpServletResponse response, String name, String value, String domain, String path, int maxAge, boolean isHttpOnly){
        Cookie cookie = new Cookie(name,value);
        if(domain != null){
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(isHttpOnly);
        response.addCookie(cookie);
    }

    /**
     * 设置
     * @param response
     * @param name
     * @param value
     */
    public synchronized static void set(HttpServletResponse response, String name, String value){
        setCookie(response, name, value, null, COOKIE_PATH, COOKIE_MAX_AGE, false);
    }

    /**
     * 根据name查询cookie
     * @param request
     * @param name
     * @return
     */
    private static Cookie getCookie(HttpServletRequest request, String name){
        Cookie[] cookiesArray = request.getCookies();
        if(cookiesArray != null && cookiesArray.length >0){
            for (Cookie cookie:cookiesArray){
                if(cookie.getName().equals(name)){
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 根据name，查询该cookie的value
     * @param request
     * @param name
     * @return
     */
    public synchronized static String getCookieValue(HttpServletRequest request, String name){
        Cookie cookie = getCookie(request, name);
        if(cookie != null){
            return cookie.getValue();
        }else{
            return null;
        }
    }

    /**
     * 根据name删除cookie,设置maxAge为0
     * @param request
     * @param response
     * @param name
     */
    public synchronized static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name){
        Cookie cookie = getCookie(request, name);
        if(cookie != null){
            setCookie(response,name,"",null,COOKIE_PATH,0,true);
        }
    }
}
