package cn.i4.data.cloud.system.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * 错误路径的返回页面
 * @author wangjc
 * @title: WebErrorController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1116:14
 */
@Configuration
@Controller
public class WebErrorController extends WebBaseController implements ErrorController {

    private final static Logger logger = LoggerFactory.getLogger(WebErrorController.class);

    @Override
    public String getErrorPath() {
        return null;
    }

    @RequestMapping(value = "/error", produces = "text/html")
    public ModelAndView errView(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object errorMess = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        logger.debug("错误信息：[{}]",errorMess);

        if(status != null){
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return getModelAndView("/error/404");
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return getModelAndView("/error/5xx");
            }
        }
        return getModelAndView("/error/404");
    }

}
