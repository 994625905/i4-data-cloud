package cn.i4.data.cloud.activiti.design.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * 错误路径的返回页面
 * @author wangjc
 * @title: ErrorController
 * @projectName i4-dp
 * @description: TODO
 * @date 2019/9/2517:59
 */
@Configuration
@Controller
public class WebErrorController implements ErrorController {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebErrorController.class);

    @Override
    public String getErrorPath() {
        return null;
    }


    @RequestMapping(value = "/error")
    public ModelAndView errView(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMess = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        LOGGER.info("状态：[{}]，错误信息：[{}]",status,errorMess);

        return new ModelAndView("/error/404");
    }

}
