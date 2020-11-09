package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 加载请求日志的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/requestLog/index")
    @RequestPermission(value = "logCache:requestLog/index")
    public ModelAndView requestLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/logCache/requestLog_index", request);
        view.addObject("userList",iUserService.selectListNotUserId(getUser(request).getId()));
        return view;
    }

    /**
     * 加载限流日志的首页
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
     * 加载权限验证日志的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/permissionLog/index")
    @RequestPermission(value = "logCache:permissionLog/index")
    public ModelAndView permissionLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/logCache/permissionLog_index",request);
        view.addObject("userList",iUserService.selectListNotUserId(getUser(request).getId()));
        return view;
    }
}
