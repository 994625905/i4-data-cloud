package cn.i4.data.cloud.system.web.action.attendanceCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AttendanceDayDto;
import cn.i4.data.cloud.core.entity.model.AttendanceDayModel;
import cn.i4.data.cloud.core.entity.view.AttendanceDayView;
import cn.i4.data.cloud.core.service.IAttendanceDayService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 考勤中心--考勤记录汇总的控制层
 * @author wangjc
 * @title: AttendanceDayController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/23-15:27
 */
@RestController
@RequestMapping(value = "/attendanceCenter/attendanceDayLog")
public class AttendanceDayLogController extends WebBaseController {

    private static final String MODULE_NAME = "考勤中心--考勤记录汇总";
    private static final String KEY_PREFIX = "/attendanceCenter/attendanceDayLog";
    @Autowired
    private IAttendanceDayService iAttendanceDayService;

    /**
     * 加载表格
     * @param dayDto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "attendanceCenter:attendanceDayLog/loadTable")
    public ActionResult<IPage<AttendanceDayView>> loadTable(AttendanceDayDto dto, HttpServletRequest request){
        IPage<AttendanceDayView> page = iAttendanceDayService.selectAll(dto);
        return ActionResult.ok(page);
    }

    /**
     * 改变状态，取消和恢复
     * @param dto
     * @return
     */
    @PostMapping(value = "/changeStatus")
    @RequestLog(module = MODULE_NAME,content = "改变状态",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--改变状态",key = KEY_PREFIX+"/changeStatus")
    @RequestPermission(value = "attendanceCenter:attendanceDayLog/changeStatus")
    public ActionResult<Boolean> changeStatus(@RequestBody AttendanceDayDto dto){
        AttendanceDayModel model = dto.getModel();
        boolean modify = iAttendanceDayService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("改变结算状态失败");
    }

    /**
     * 调整修改，调整对应打卡记录
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "调整修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--调整修改",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "attendanceCenter:attendanceDayLog/update")
    public ActionResult<Boolean> update(@RequestBody AttendanceDayDto dto){
        AttendanceDayModel model = dto.getModel();
        boolean modify = iAttendanceDayService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("调整修改失败");
    }

}
