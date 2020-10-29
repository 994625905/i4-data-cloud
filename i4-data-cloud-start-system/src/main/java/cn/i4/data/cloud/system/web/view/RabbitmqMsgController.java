package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.core.entity.dto.SetRabbitmqExchangeDto;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqQueueDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqExchangeModel;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import cn.i4.data.cloud.core.service.ISetRabbitmqExchangeService;
import cn.i4.data.cloud.core.service.ISetRabbitmqQueueService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * rabbitmq的模型页面层
 * @author wangjc
 * @title: RabbitMqMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/2120:42
 */
@RequestMapping(value = "/rabbitmqMsg")
@Controller
public class RabbitmqMsgController extends WebBaseController {

    @Autowired
    private ISetRabbitmqExchangeService iSetRabbitmqExchangeService;
    @Autowired
    private ISetRabbitmqQueueService iSetRabbitmqQueueService;

    /**
     * 交换机管理的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/exchangeMsg/index")
    public ModelAndView exchangeIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/rabbitmqMsg/exchangeMsg_index", request);
        return view;
    }

    /**
     * 交换机的新增页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/exchangeMsg/addPage")
    public ModelAndView exchangeMsgAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/rabbitmqMsg/exchangeMsg_add", request);
        return view;
    }

    /**
     * 交换机的编辑页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/exchangeMsg/editPage")
    public ModelAndView exchangeMsgEditPage(SetRabbitmqExchangeDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/rabbitmqMsg/exchangeMsg_edit", request);
        view.addObject("model",this.iSetRabbitmqExchangeService.getById(dto.getId()));
        return view;
    }

    /**
     * 队列管理的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/queueMsg/index")
    public ModelAndView queueMsgIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/rabbitmqMsg/queueMsg_index", request);
        return view;
    }

    /**
     * 队列管理的新增页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/queueMsg/addPage")
    public ModelAndView queueMsgAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/rabbitmqMsg/queueMsg_add", request);
        view.addObject("exchangeList",iSetRabbitmqExchangeService.list());
        return view;
    }

    /**
     * 队列管理的修改页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/queueMsg/editPage")
    public ModelAndView queueMsgEditPage(SetRabbitmqQueueDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/rabbitmqMsg/queueMsg_edit", request);
        view.addObject("model",iSetRabbitmqQueueService.getById(dto.getId()));
        view.addObject("exchangeList",iSetRabbitmqExchangeService.list());
        return view;
    }

}
