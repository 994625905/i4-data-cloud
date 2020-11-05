package cn.i4.data.cloud.auth.web;

import cn.hutool.core.convert.Convert;
import cn.i4.data.cloud.auth.help.AuthHelp;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.mongo.core.entity.MongoSystemConstant;
import cn.i4.data.cloud.mongo.core.service.MongoSystemConstantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证中心的页面
 * @author wangjc
 * @title: MainController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1716:00
 */
@Controller
@RequestMapping(value = "/auth/page")
public class AuthMainController extends WebBaseController{

    private static final Logger logger = LoggerFactory.getLogger(AuthMainController.class);
    @Autowired
    private MongoSystemConstantService mongoSystemConstantService;

    /**
     * 认证系统主页
     * @param request
     * @param redirect：重定向地址
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, String redirect){
        UserModel userModel = AuthHelp.loginCheck(request, redisService);

        if(userModel == null){
            ModelAndView view = getModelAndView("/login/login",request);
            view.addObject("redirect", redirect);

            MongoSystemConstant content = mongoSystemConstantService.selectByMongoId(Convert.toStr(this.getSystemConstant().get("authInfo")));
            view.addObject("content",content==null?"":content.getContent());

            logger.debug("登录请求，站点[{}]",redirect);

            return view;
        }
        if(redirect == null){
            return getModelAndView("/index");
        }
        return getModelAndView("redirect:"+redirect);
    }

    /**
     * 注销登录，重定向请求过来的
     * @param authorization
     * @return
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout(String authorization,String redirect){
        AuthHelp.logout(authorization, redisService);
        return getModelAndView("redirect:"+authURL+"?redirect="+redirect);
    }

    /**
     * 忘记密码页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/forgetPassword")
    public ModelAndView forgetPassword(HttpServletRequest request){
        ModelAndView view = getModelAndView("/login/forgetPassword", request);
        return view;
    }

    /**
     * 注册页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/register")
    public ModelAndView register(HttpServletRequest request){
        ModelAndView view = getModelAndView("/login/register", request);
        return view;
    }

}
