package cn.i4.data.cloud.system.web.action.attendanceCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AttendanceYearDto;
import cn.i4.data.cloud.core.entity.view.AttendanceYearView;
import cn.i4.data.cloud.core.service.IAttendanceYearService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 考勤中心--年总结汇总的控制层
 * @author wangjc
 * @title: AttendanceYearLogController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/25-18:02
 */
@RestController
@RequestMapping(value = "/attendanceCenter/attendanceYearLog")
public class AttendanceYearLogController extends WebBaseController {

    private static final String MODULE_NAME = "考勤中心--年总结汇总";
    private static final String KEY_PREFIX = "/attendanceCenter/attendanceYear";
    @Autowired
    private IAttendanceYearService iAttendanceYearService;

    /**
     * 加载表格
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "attendanceCenter:attendanceYearLog/loadTable")
    public ActionResult<IPage<AttendanceYearView>> loadTable(AttendanceYearDto dto){
        IPage<AttendanceYearView> page = iAttendanceYearService.selectAllLog(dto);
        return ActionResult.ok(page);
    }

}
