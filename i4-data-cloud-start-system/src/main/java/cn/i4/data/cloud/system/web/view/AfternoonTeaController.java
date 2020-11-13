package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaDto;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaSelectModel;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaMenuView;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;
import cn.i4.data.cloud.core.service.*;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private IAfternoonTeaTaskService iAfternoonTeaTaskService;
    @Autowired
    private IAfternoonTeaMenuService iAfternoonTeaMenuService;
    @Autowired
    private IAfternoonTeaSelectService iAfternoonTeaSelectService;

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
     * 加载下午茶列表选择页
     * @param request
     * @return
     */
    @RequestMapping(value = "/list/selectPage")
    @RequestPermission(value = "afternoonTea:list/selectPage")
    public ModelAndView listSelect(HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/list_select", request);
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
     * 加载点单任务的点单区
     * @param request
     * @return
     */
    @RequestMapping(value = "/task/orderPage")
    @RequestPermission(value = "afternoonTea:task/orderPage")
    public ModelAndView taskOrderPage(AfternoonTeaTaskDto dto, HttpServletRequest request){

        AfternoonTeaTaskModel model = iAfternoonTeaTaskService.getById(dto.getId());
        if(model.getEndTime() <= System.currentTimeMillis()/1000L){
            return getTimeOutView();
        }
        List<AfternoonTeaMenuView> menuList = iAfternoonTeaMenuService.getListByTaskId(model.getId());

        /** 做新增处理 */
        List<AfternoonTeaSelectModel> selectList = iAfternoonTeaSelectService.getListByTaskId(model.getId());

        ModelAndView view = getModelAndView("/afternoonTea/task_order", request);
        view.addObject("model",model);
        view.addObject("menuList",menuList);
        view.addObject("selectList",selectList);
        return view;
    }

    /**
     * 加载点单任务的详情页（有权限的可看）
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/task/detailPage")
    @RequestPermission(value = "afternoonTea:task/detailPage")
    public ModelAndView taskDetailPage(AfternoonTeaTaskDto dto,HttpServletRequest request){

        AfternoonTeaTaskModel model = iAfternoonTeaTaskService.getById(dto.getId());
        List<AfternoonTeaMenuView> menuList = iAfternoonTeaMenuService.getListByTaskId(model.getId());
        List<Integer> menuIdList = new ArrayList<Integer>(){{
            for(AfternoonTeaMenuView menu:menuList){
                add(menu.getId());
            }
        }};

        ModelAndView view = getModelAndView("/afternoonTea/task_detail", request);
        view.addObject("model",model);
        view.addObject("menuList",menuList);
        view.addObject("menuIdList", JSONObject.toJSONString(menuIdList));
        return view;
    }

    /**
     * 加载点单任务发布的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/taskDeploy/index")
    @RequestPermission(value = "afternoonTea:taskDeploy/index")
    public ModelAndView taskDeployIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/taskDeploy_index", request);
        return view;
    }

    /**
     * 加载点单任务发布的新增页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/taskDeploy/addPage")
    @RequestPermission(value = "afternoonTea:taskDeploy/addPage")
    public ModelAndView taskDeployAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/afternoonTea/taskDeploy_add", request);
        return view;
    }

    /**
     * 加载点单任务发布的修改页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/taskDeploy/editPage")
    @RequestPermission(value = "afternoonTea:taskDeploy/editPage")
    public ModelAndView taskDeployEditPage(AfternoonTeaTaskDto dto, HttpServletRequest request){

        AfternoonTeaTaskModel model = iAfternoonTeaTaskService.getById(dto.getId());
        if(model.getEndTime() <= System.currentTimeMillis()/1000L){
            return getTimeOutView();
        }
        List<AfternoonTeaMenuView> menuList = iAfternoonTeaMenuService.getListByTaskId(model.getId());

        ModelAndView view = getModelAndView("/afternoonTea/taskDeploy_edit", request);
        view.addObject("model",model);
        view.addObject("menuList",menuList);
        return view;
    }

    /**
     * 加载点单任务发布的详情页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/taskDeploy/detailPage")
    @RequestPermission(value = "afternoonTea:taskDeploy/detailPage")
    public ModelAndView taskDeployDetailPage(AfternoonTeaTaskDto dto, HttpServletRequest request){


        AfternoonTeaTaskModel model = iAfternoonTeaTaskService.getById(dto.getId());
        if(model.getEndTime() <= System.currentTimeMillis()/1000L){
            model.setStatus(2);
        }
        List<AfternoonTeaMenuView> menuList = iAfternoonTeaMenuService.getListByTaskId(model.getId());

        ModelAndView view = getModelAndView("/afternoonTea/taskDeploy_detail", request);
        view.addObject("model",model);
        view.addObject("menuList",menuList);
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
