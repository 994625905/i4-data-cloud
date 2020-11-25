package cn.i4.data.cloud.system.web.action.attendanceCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AttendanceMonthDto;
import cn.i4.data.cloud.core.entity.view.AttendanceMonthView;
import cn.i4.data.cloud.core.service.IAttendanceMonthService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 考勤中心--月结算汇总的控制层
 * @author wangjc
 * @title: AttendanceMonthLogController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/24-17:53
 */
@RestController
@RequestMapping(value = "/attendanceCenter/attendanceMonthLog")
public class AttendanceMonthLogController extends WebBaseController {

    private static final String MODULE_NAME = "考勤中心--月结算汇总";
    private static final String KEY_PREFIX = "/attendanceCenter/attendanceMonthLog";
    @Autowired
    private IAttendanceMonthService iAttendanceMonthService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "attendanceCenter:attendanceMonthLog/loadTable")
    public ActionResult<IPage<AttendanceMonthView>> loadTable(AttendanceMonthDto dto){
        IPage<AttendanceMonthView> page = iAttendanceMonthService.selectAllLog(dto);
        return ActionResult.ok(page);
    }

    /**
     * 逐一校对核算日期
     * @param dto
     * @return
     */
    @PostMapping(value = "/settleOne")
    @RequestLog(module = MODULE_NAME,content = "逐一校对核算日期",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--逐一校对核算日期",key = KEY_PREFIX+"/settleOne")
    @RequestPermission(value = "attendanceCenter:attendanceMonthLog/settleOne")
    public ActionResult<Boolean> settleOne(AttendanceMonthDto dto){

        return null;
    }

    /**
     * 统一校对核算日期
     * @param dto
     * @return
     */
    @PostMapping(value = "/settleAll")
    @RequestLog(module = MODULE_NAME,content = "统一校对核算日期",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--统一校对核算日期",key = KEY_PREFIX+"/settleAll")
    @RequestPermission(value = "attendanceCenter:attendanceMonthLog/settleAll")
    public ActionResult<Boolean> settleAll(AttendanceMonthDto dto){

        return null;
    }

}
