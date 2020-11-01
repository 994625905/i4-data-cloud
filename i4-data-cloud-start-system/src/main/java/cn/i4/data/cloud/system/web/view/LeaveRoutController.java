package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.core.entity.model.LeaveTypeModel;
import cn.i4.data.cloud.core.service.ILeaveTypeService;
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

    /**
     * 加载请假类型的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/leaveType/index")
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
    public ModelAndView leaveApplyIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/leaveRout/leaveApply_index", request);
        view.addObject("typeList",iLeaveTypeService.list());
        return view;
    }

}
