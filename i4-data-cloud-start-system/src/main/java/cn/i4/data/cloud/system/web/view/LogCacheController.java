package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.LogRequestDto;
import cn.i4.data.cloud.core.entity.view.LogRequestView;
import cn.i4.data.cloud.core.service.ILogRequestService;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日志捕获的模型视图页面
 * @author wangjc
 * @title: LogCacheController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/9-17:28
 */
@Controller
@RequestMapping(value = "/logCache")
public class LogCacheController extends WebBaseController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ILogRequestService iLogRequestService;

    /**
     * 加载请求日志汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/requestLog/index")
    @RequestPermission(value = "logCache:requestLog/index")
    public ModelAndView requestLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/logCache/requestLog_index", request);
        view.addObject("userList",iUserService.list());
        return view;
    }

    /**
     * 加载请求日志的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/request/index")
    @RequestPermission(value = "logCache:request/index")
    public ModelAndView requestIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/logCache/request_index", request);
        return view;
    }

    /**
     * 加载请求日志的详情页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/request/detailPage")
    @RequestPermission(value = "logCache:request/detailPage")
    public ModelAndView requestDetailPage(LogRequestDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/logCache/request_detail", request);

        dto.setUserId(getUser(request).getId());
        List<LogRequestView> list = iLogRequestService.selectByUserIdAndDate(dto);
        view.addObject("list",list);
        return view;
    }

    /**
     * 加载限流日志汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/limitLog/index")
    @RequestPermission(value = "logCache:limitLog/index")
    public ModelAndView limitLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/logCache/limitLog_index", request);
        return view;
    }

    /**
     * 加载权限验证日志汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/permissionLog/index")
    @RequestPermission(value = "logCache:permissionLog/index")
    public ModelAndView permissionLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/logCache/permissionLog_index",request);
        view.addObject("userList",iUserService.list());
        return view;
    }
}
