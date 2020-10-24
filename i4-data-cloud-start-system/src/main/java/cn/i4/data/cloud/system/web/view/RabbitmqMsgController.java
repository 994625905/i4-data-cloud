package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * rabbitmq的模型页面层
 * @author wangjc
 * @title: RabbitMqMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/2120:42
 */
@RequestMapping(value = "/rabbitmqMsg")
public class RabbitmqMsgController extends WebBaseController {


    public ModelAndView exchangeIndex(HttpServletRequest request){

        return null;
    }

}
