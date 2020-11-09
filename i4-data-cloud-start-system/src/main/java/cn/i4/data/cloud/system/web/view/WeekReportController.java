package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.WeekreportDto;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.core.service.IWeekreportProcessNodeService;
import cn.i4.data.cloud.core.service.IWeekreportService;
import cn.i4.data.cloud.mongo.core.service.MongoWeekReportService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 周报事务的模型视图页面
 * @author wangjc
 * @title: WeekReportController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/5-18:05
 */
@RequestMapping(value = "/weekReport")
@Controller
public class WeekReportController extends WebBaseController {

    @Autowired
    private IWeekreportService iWeekreportService;
    @Autowired
    private IWeekreportProcessNodeService iWeekreportProcessNodeService;
    @Autowired
    private MongoWeekReportService mongoWeekReportService;
    @Autowired
    private IUserService iUserService;

    /**
     * 加载周报提交的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/weekReportApply/index")
    @RequestPermission(value = "weekReport:weekReportApply/index")
    public ModelAndView weekReportApplyIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/weekReport/weekReportApply_index", request);
        return view;
    }

    /**
     * 加载周报提交的新增页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/weekReportApply/addPage")
    @RequestPermission(value = "weekReport:weekReportApply/addPage")
    public ModelAndView weekReportApplyAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/weekReport/weekReportApply_add", request);
        return view;
    }

    /**
     * 加载周报提交的编辑页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/weekReportApply/editPage")
    @RequestPermission(value = "weekReport:weekReportApply/editPage")
    public ModelAndView weekReportApplyEditPage(WeekreportDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/weekReport/weekReportApply_edit", request);
        view.addObject("model",iWeekreportService.getById(dto.getId()));
        view.addObject("weekReport",mongoWeekReportService.selectByMongoId(dto.getMongoId()));
        return view;
    }

    /**
     * 加载周报提交的预览页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/weekReportApply/readPage")
    @RequestPermission(value = "weekReport:weekReportApply/readPage")
    public ModelAndView weekReportApplyReadPage(WeekreportDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/weekReport/weekReportApply_read", request);
        view.addObject("model",iWeekreportService.selectRealNameById(dto.getId()));
        view.addObject("weekReport",mongoWeekReportService.selectByMongoId(dto.getMongoId()));
        return view;
    }

    /**
     * 加载周报记录的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/weekReportLog/index")
    @RequestPermission(value = "weekReport:weekReportLog/index")
    public ModelAndView weekReportLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/weekReport/weekReportLog_index", request);
        view.addObject("userList",iUserService.selectListNotUserId(this.getUser(request).getId()));
        return view;
    }

    /**
     * 加载周报记录的预览页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/weekReportLog/readPage")
    @RequestPermission(value = "weekReport:weekReportLog/readPage")
    public ModelAndView weekReportLogReadPage(WeekreportDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/weekReport/weekReportLog_read", request);
        view.addObject("model",iWeekreportService.selectRealNameById(dto.getId()));
        view.addObject("weekReport",mongoWeekReportService.selectByMongoId(dto.getMongoId()));
        return view;
    }

    /**
     * 加载周报流程日志页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/weekReportProcess/logPage")
    @RequestPermission(value = "weekReport:weekReportProcess/logPage")
    public ModelAndView weekReportProcessLogPage(WeekreportDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/weekReport/weekReportProcess_log", request);
        view.addObject("param",dto);
        view.addObject("nodeList",iWeekreportProcessNodeService.selectByProcessId(dto.getProcessId()));
        return view;
    }

}
