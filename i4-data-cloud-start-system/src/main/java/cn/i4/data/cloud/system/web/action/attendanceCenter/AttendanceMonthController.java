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

import javax.servlet.http.HttpServletRequest;

/**
 * 考勤中心--月结算的控制层
 * @author wangjc
 * @title: AttendanceMonthController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/24-17:12
 */
@RestController
@RequestMapping(value = "/attendanceCenter/attendanceMonth")
public class AttendanceMonthController extends WebBaseController {

    private static final String MODULE_NAME = "考勤中心--月结算";
    private static final String KEY_PREFIX = "/attendanceCenter/attendanceMonth";
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
    @RequestPermission(value = "attendanceCenter:attendanceMonth/loadTable")
    public ActionResult<IPage<AttendanceMonthView>> loadTable(AttendanceMonthDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());

        IPage<AttendanceMonthView> page = iAttendanceMonthService.selectPage(dto);
        return ActionResult.ok(page);
    }


}
