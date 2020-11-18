package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.LeaveDto;
import cn.i4.data.cloud.core.entity.model.LeaveTypeModel;
import cn.i4.data.cloud.core.service.*;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 请假事务的模型视图页面
 * @author wangjc
 * @title: LeaveRoutController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/1-15:04
 */
@Controller
@RequestMapping(value = "/leaveRout")
public class LeaveRoutController extends WebBaseController {

    @Autowired
    private ILeaveTypeService iLeaveTypeService;
    @Autowired
    private ILeaveService iLeaveService;
    @Autowired
    private ILeaveProcessNodeService iLeaveProcessNodeService;
    @Autowired
    private IVActReDeployProcdefService ivActReDeployProcdefService;

    /**
     * 加载请假类型的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/leaveType/index")
    @RequestPermission(value = "leaveRout:leaveType/index")
    public ModelAndView leaveTypeIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveType_index", request);
        return view;
    }

    /**
     * 加载请假模板首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/leaveBoard/index")
    @RequestPermission(value = "leaveRout:leaveBoard/index")
    public ModelAndView leaveBoardIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveBoard_index", request);
        List<LeaveTypeModel> list = iLeaveTypeService.list();
        view.addObject("typeList",list);
        return view;
    }

    /**
     * 加载请假申请模块的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/leaveApply/index")
    @RequestPermission(value = "leaveRout:leaveApply/index")
    public ModelAndView leaveApplyIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveApply_index", request);
        view.addObject("typeList",iLeaveTypeService.list());
        return view;
    }

    /**
     * 加载请假申请新增页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/leaveApply/addPage")
    @RequestPermission(value = "leaveRout:leaveApply/addPage")
    public ModelAndView leaveApplyAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveApply_add", request);
        view.addObject("typeList",iLeaveTypeService.list());
        return view;
    }

    /**
     * 加载请假申请修改页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/leaveApply/editPage")
    @RequestPermission(value = "leaveRout:leaveApply/editPage")
    public ModelAndView leaveApplyEditPage(LeaveDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveApply_edit", request);
        view.addObject("typeList",iLeaveTypeService.list());
        view.addObject("model",iLeaveService.getById(dto.getId()));
        return view;
    }

    /**
     * 发送请假申请
     * @return
     */
    @RequestMapping(value = "/leaveApply/applyPage")
    @RequestPermission(value = "leaveRout:leaveApply/applyPage")
    public ModelAndView leaveApplyApplyPage(LeaveDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveApply_apply", request);
        view.addObject("processList",ivActReDeployProcdefService.list());
        view.addObject("model",iLeaveService.getById(dto.getId()));
        view.addObject("userList",iUserService.selectListNotUserId(getUser(request).getId()));
        return view;
    }

    /**
     * 加载请假流程日志的页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/leaveProcess/logPage")
    @RequestPermission(value = "leaveRout:leaveProcess/logPage")
    public ModelAndView leaveProcessLogPage(LeaveDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveProcess_log", request);
        view.addObject("param",dto);
        view.addObject("nodeList",iLeaveProcessNodeService.selectByProcessId(dto.getProcessId()));
        return view;
    }

    /**
     * 加载请求记录的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/leaveLog/index")
    @RequestPermission(value = "leaveRout:leaveLog/index")
    public ModelAndView leaveLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveLog_index", request);
        view.addObject("typeList",iLeaveTypeService.list());
        view.addObject("userList",iUserService.list());
        return view;
    }
}
