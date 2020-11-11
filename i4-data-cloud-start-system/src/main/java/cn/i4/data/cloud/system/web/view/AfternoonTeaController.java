package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaDto;
import cn.i4.data.cloud.core.service.IAfternoonTeaService;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 下午茶的模型视图页面
 * @author wangjc
 * @title: AfternoonTeaController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/10-15:05
 */
@Controller
@RequestMapping(value = "/afternoonTea")
public class AfternoonTeaController extends WebBaseController {

    @Autowired
    private IAfternoonTeaService iAfternoonTeaService;
    @Autowired
    private IUserService iUserService;

    /**
     * 加载下午茶列表的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/list/index")
    @RequestPermission(value = "afternoonTea:list/index")
    public ModelAndView listIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/list_index", request);
        return view;
    }

    /**
     * 加载下午列表的新增页
     * @param request
     * @return
     */
    @RequestMapping(value = "/list/addPage")
    @RequestPermission(value = "afternoonTea:list/addPage")
    public ModelAndView listAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/list_add", request);
        return view;
    }

    /**
     * 加载下午列表的修改页
     * @param request
     * @return
     */
    @RequestMapping(value = "/list/editPage")
    @RequestPermission(value = "afternoonTea:list/editPage")
    public ModelAndView listEditPage(AfternoonTeaDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/list_edit", request);
        view.addObject("model", iAfternoonTeaService.getById(dto.getId()));
        return view;
    }

    /**
     * 加载点单任务的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/task/index")
    @RequestPermission(value = "afternoonTea:task/index")
    public ModelAndView taskIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/task_index", request);
        return view;
    }

    /**
     * 加载个人点单记录的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/select/index")
    @RequestPermission(value = "afternoonTea:select/index")
    public ModelAndView selectIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/select_index", request);
        return view;
    }

    /**
     * 加载点单记录汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectLog/index")
    @RequestPermission(value = "afternoonTea:selectLog/index")
    public ModelAndView selectLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/selectLog_index", request);
        view.addObject("userList",iUserService.list());
        return view;
    }


}
