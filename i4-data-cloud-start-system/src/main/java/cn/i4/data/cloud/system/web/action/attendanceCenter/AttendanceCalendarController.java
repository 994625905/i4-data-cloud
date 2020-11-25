package cn.i4.data.cloud.system.web.action.attendanceCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AttendanceCalendarDto;
import cn.i4.data.cloud.core.entity.view.AttendanceCalendarView;
import cn.i4.data.cloud.core.service.IAttendanceCalendarService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 考勤中心--日历设置的控制层
 * @author wangjc
 * @title: AttendanceCalendarController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/25-9:42
 */
@RestController
@RequestMapping(value = "/attendanceCenter/attendanceCalendar")
public class AttendanceCalendarController extends WebBaseController {

    private static final String MODULE_NAME = "考勤中心--日历设置";
    private static final String KEY_PREFIX = "/attendanceCenter/attendanceCalendar";
    @Autowired
    private IAttendanceCalendarService iAttendanceCalendarService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "attendanceCenter:attendanceCalendar/loadTable")
    public ActionResult<IPage<AttendanceCalendarView>> loadTable(AttendanceCalendarDto dto){
        IPage<AttendanceCalendarView> page = iAttendanceCalendarService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "attendanceCenter:attendanceCalendar/delete")
    public ActionResult<Boolean> delete(AttendanceCalendarDto dto){
        boolean remove = iAttendanceCalendarService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(true);
        }
        return ActionResult.error("删除失败");
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "attendanceCenter:attendanceCalendar/insert")
    public ActionResult<Boolean> insert(@RequestBody AttendanceCalendarDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());

        try {
            Boolean insert = iAttendanceCalendarService.insert(dto);
            return ActionResult.ok(insert);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }
}
