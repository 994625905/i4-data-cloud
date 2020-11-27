package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.core.entity.dto.AttendanceCalendarDto;
import cn.i4.data.cloud.core.entity.dto.AttendanceDayDto;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendDto;
import cn.i4.data.cloud.core.entity.model.AttendanceDayModel;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.entity.view.AttendanceDayView;
import cn.i4.data.cloud.core.service.*;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 考勤中心的模型视图页面
 * @author wangjc
 * @title: AttendanceCenterController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/23-14:04
 */
@Controller
@RequestMapping(value = "/attendanceCenter")
public class AttendanceCenterController extends WebBaseController {

    @Autowired
    private IAttendanceDayService iAttendanceDayService;
    @Autowired
    private IAttendanceMendService iAttendanceMendService;
    @Autowired
    private IAttendanceMendProcessNodeService iAttendanceMendProcessNodeService;
    @Autowired
    private IVActReDeployProcdefService ivActReDeployProcdefService;
    @Autowired
    private IAttendanceCalendarService iAttendanceCalendarService;

    /**
     * 加载打卡记录首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceRecord/index")
    @RequestPermission(value = "attendanceCenter:attendanceRecord/index")
    public ModelAndView attendanceRecordIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceRecord_index", request);
        return view;
    }

    /**
     * 加载打卡记录汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceRecordLog/index")
    @RequestPermission(value = "attendanceCenter:attendanceRecordLog/index")
    public ModelAndView attendanceRecordLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceRecordLog_index", request);
        view.addObject("userList",iUserService.list());
        return view;
    }

    /**
     * 加载考勤记录的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceDay/index")
    @RequestPermission(value = "attendanceCenter:attendanceDay/index")
    public ModelAndView attendanceDayIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceDay_index", request);
        return view;
    }

    /**
     * 加载考勤记录汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceDayLog/index")
    @RequestPermission(value = "attendanceCenter:attendanceDayLog/index")
    public ModelAndView attendanceDayLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceDayLog_index", request);
        view.addObject("userList",iUserService.list());
        return view;
    }

    /**
     * 加载考勤记录汇总的调整页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceDayLog/updatePage")
    @RequestPermission(value = "attendanceCenter:attendanceDayLog/updatePage")
    public ModelAndView attendanceDayLogUpdatePage(AttendanceDayDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceDayLog_update", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 加载补卡申请的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMend/index")
    @RequestPermission(value = "attendanceCenter:attendanceMend/index")
    public ModelAndView attendanceMendIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMend_index", request);
        return view;
    }

    /**
     * 加载补卡申请的新增页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMend/addPage")
    @RequestPermission(value = "attendanceCenter:attendanceMend/addPage")
    public ModelAndView attendanceMendAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMend_add", request);

        List<AttendanceDayModel> list = iAttendanceDayService.selectMendList(this.getUser(request).getId());
        view.addObject("dayList",list);
        return view;
    }

    /**
     * 加载补卡申请的编辑页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMend/editPage")
    @RequestPermission(value = "attendanceCenter:attendanceMend/editPage")
    public ModelAndView attendanceMendEditPage(AttendanceMendDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMend_edit", request);

        List<AttendanceDayModel> list = iAttendanceDayService.selectMendList(this.getUser(request).getId());
        view.addObject("dayList",list);
        view.addObject("model",iAttendanceDayService.getById(dto.getId()));
        return view;
    }

    /**
     * 加载补卡申请的申请页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMend/applyPage")
    @RequestPermission(value = "attendanceCenter:attendanceMend/applyPage")
    public ModelAndView attendanceMendApplyPage(AttendanceMendDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("", request);

        List<UserModel> userModelList = iUserService.selectListNotUserId(getUser(request).getId());
        view.addObject("userList", JSONObject.toJSONString(userModelList));
        view.addObject("processList",ivActReDeployProcdefService.list());
        view.addObject("model",iAttendanceMendService.selectById(dto.getId()));
        return view;
    }

    /**
     * 加载补卡申请的流程日志
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMend/logPage")
    @RequestPermission(value = "attendanceCenter:attendanceMend/logPage")
    public ModelAndView attendanceMendLogPage(AttendanceMendDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMend_log", request);

        view.addObject("param",dto);
        view.addObject("nodeList",iAttendanceMendProcessNodeService.selectByProcessId(dto.getProcessId()));
        return view;
    }

    /**
     * 加载补卡申请的明确条件页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMend/where")
    @RequestPermission(value = "attendanceCenter:attendanceMend/where")
    public ModelAndView attendanceMendWhere(AttendanceMendDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMend_where", request);

        view.addObject("param",dto);
        return view;
    }

    /**
     * 加载补卡申请汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMendLog/index")
    @RequestPermission(value = "attendanceCenter:attendanceMendLog/index")
    public ModelAndView attendanceMendLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMendLog_index", request);
        view.addObject("userList",iUserService.list());
        return view;
    }

    /**
     * 加载补卡申请汇总的流程日志
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMendLog/logPage")
    @RequestPermission(value = "attendanceCenter:attendanceMendLog/logPage")
    public ModelAndView attendanceMendLogLogPage(AttendanceMendDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMendLog_log", request);

        view.addObject("param",dto);
        view.addObject("nodeList",iAttendanceMendProcessNodeService.selectByProcessId(dto.getProcessId()));
        return view;
    }

    /**
     * 加载月结算的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMonth/index")
    @RequestPermission(value = "attendanceCenter:attendanceMonth/index")
    public ModelAndView attendanceMonthIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMonth_index", request);
        return view;
    }

    /**
     * 加载月结算汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceMonthLog/index")
    @RequestPermission(value = "attendanceCenter:attendanceMonthLog/index")
    public ModelAndView attendanceMonthLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceMonthLog_index", request);
        return view;
    }

    /**
     * 加载日历设置的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceCalendar/index")
    @RequestPermission(value = "attendanceCenter:attendanceCalendar/index")
    public ModelAndView attendanceCalendarIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceCalendar_index", request);
        return view;
    }

    /**
     * 加载日历设置的新增页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceCalendar/addPage")
    @RequestPermission(value = "attendanceCenter:attendanceCalendar/addPage")
    public ModelAndView attendanceCalendarAddPage(AttendanceCalendarDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceCalendar_add", request);
        view.addObject("param",dto);
        return view;
    }

    /**
     * 加载年总结的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceYear/index")
    @RequestPermission(value = "attendanceCenter:attendanceYear/index")
    public ModelAndView attendanceYearIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceYear_index", request);
        return view;
    }

    /**
     * 加载年总结汇总的首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/attendanceYearLog/index")
    @RequestPermission(value = "attendanceCenter:attendanceYearLog/index")
    public ModelAndView attendanceYearLogIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/attendanceCenter/attendanceYearLog_index", request);
        view.addObject("userList",iUserService.list());
        return view;
    }
}
