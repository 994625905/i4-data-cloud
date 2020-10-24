package cn.i4.data.cloud.auth.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 * @author wangjc
 * @title: AuthLoginFilter
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/109:56
 */
public class AuthLoginFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(AuthLoginFilter.class);

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("过滤器初始化：[{}]",filterConfig);
    }

    /**
     * 核心过滤，资源放行
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        chain.doFilter(request,response);
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {

    }

}
