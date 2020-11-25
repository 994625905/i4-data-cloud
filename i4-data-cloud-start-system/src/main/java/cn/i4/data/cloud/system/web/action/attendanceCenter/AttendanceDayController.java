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
 * 考勤中心--考勤记录的控制层
 * @author wangjc
 * @title: AttendanceDayController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/23-15:27
 */
@RestController
@RequestMapping(value = "/attendanceCenter/attendanceDay")
public class AttendanceDayController extends WebBaseController {

    private static final String MODULE_NAME = "考勤中心--考勤记录";
    private static final String KEY_PREFIX = "/attendanceCenter/attendanceDay";
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
    @RequestPermission(value = "attendanceCenter:attendanceDay/loadTable")
    public ActionResult<IPage<AttendanceDayView>> loadTable(AttendanceDayDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());
        IPage<AttendanceDayView> page = iAttendanceDayService.selectPage(dto);
        return ActionResult.ok(page);
    }

}
