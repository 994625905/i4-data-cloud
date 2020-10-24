package cn.i4.data.cloud.system.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjc
 * @title: MainController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1017:46
 */
@Controller
@RequestMapping(value = "/main")
public class MainController extends WebBaseController {

    /**
     * 统一注销地址
     */
    @Value("${i4.data.cloud.auth-url-logout}")
    private String logoutURL;
    /**
     * 重定向的地址
     */
    @Value("${i4.data.cloud.server-url}")
    private String redirectServer;

    /**
     * 主页
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView view = getModelAndView("/index", request);
        view.addObject("logout",this.logoutURL);
        view.addObject("redirect",this.redirectServer);
        return view;
    }

}
