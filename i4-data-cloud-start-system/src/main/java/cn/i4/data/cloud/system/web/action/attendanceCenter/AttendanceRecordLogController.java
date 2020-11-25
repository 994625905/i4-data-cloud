package cn.i4.data.cloud.system.web.action.attendanceCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AttendanceDto;
import cn.i4.data.cloud.core.entity.view.AttendanceView;
import cn.i4.data.cloud.core.service.IAttendanceService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 考勤中心--打卡记录汇总的控制层
 * @author wangjc
 * @title: AttendanceRecordController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/23-14:28
 */
@RequestMapping(value = "/attendanceCenter/attendanceRecordLog")
public class AttendanceRecordLogController extends WebBaseController {

    private static final String MODULE_NAME = "考勤中心--打卡记录汇总";
    private static final String KEY_PREFIX = "/attendanceCenter/attendanceRecordLog";
    @Autowired
    private IAttendanceService iAttendanceService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "attendanceCenter:attendanceRecordLog/loadTable")
    public ActionResult<IPage<AttendanceView>> loadTable(AttendanceDto dto){
        IPage<AttendanceView> page = iAttendanceService.selectAll(dto);
        return ActionResult.ok(page);
    }

}
