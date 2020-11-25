package cn.i4.data.cloud.system.web.action.attendanceCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendView;
import cn.i4.data.cloud.core.service.IAttendanceMendService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 考勤中心--补卡申请的控制层
 * @author wangjc
 * @title: AttendanceMendController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/23-20:41
 */
@RestController
@RequestMapping(value = "/attendanceCenter/attendanceMend")
public class AttendanceMendController extends WebBaseController {

    private static final String MODULE_NAME = "考勤中心--补卡申请";
    private static final String KEY_PREFIX = "/attendanceCenter/attendanceMend";
    @Autowired
    private IAttendanceMendService iAttendanceMendService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "attendanceCenter:attendanceMend/loadTable")
    public ActionResult<IPage<AttendanceMendView>> loadTable(AttendanceMendDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());

        IPage<AttendanceMendView> page = iAttendanceMendService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 加载明确条件页面的表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadWhereTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadWhereTable")
    @RequestPermission(value = "attendanceCenter:attendanceMend/loadWhereTable")
    public ActionResult<IPage<AttendanceMendView>> loadWhereTable(AttendanceMendDto dto, HttpServletRequest request){
        IPage<AttendanceMendView> page = iAttendanceMendService.selectPage(dto);
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
    @RequestPermission(value = "attendanceCenter:attendanceMend/delete")
    public ActionResult<Boolean> delete(AttendanceMendDto dto){
        boolean remove = iAttendanceMendService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(true);
        }
        return ActionResult.error("删除失败");
    }

    /**
     * 新增
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "attendanceCenter:attendanceMend/insert")
    public ActionResult<Boolean> insert(@RequestBody AttendanceMendDto dto, HttpServletRequest request){
        AttendanceMendModel model = dto.getModel();

        model.setUserId(this.getUser(request).getId());
        model.setCreateTime(System.currentTimeMillis()/1000L);
        boolean save = iAttendanceMendService.save(model);
        if(save){
            return ActionResult.ok(true);
        }
        return ActionResult.error("新增失败");
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "attendanceCenter:attendanceMend/update")
    public ActionResult<Boolean> update(@RequestBody AttendanceMendDto dto){
        AttendanceMendModel model = dto.getModel();

        boolean modify = iAttendanceMendService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("修改失败");
    }

    /**
     * 发送请假申请
     * @return
     */
    @PostMapping(value = "/apply")
    @RequestLog(module = MODULE_NAME,content = "发送请假申请",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--发送请假申请",key = KEY_PREFIX+"/apply")
    @RequestPermission(value = "attendanceCenter:attendanceMend/apply")
    public ActionResult<Boolean> apply(@RequestBody AttendanceMendDto dto, HttpServletRequest request){
        dto.setUserId(getUser(request).getId());

        /** 启动一个流程，流程变量设置为当前用户和假条ID */
//        ActionResult<String> result = processEngineMicroService.startProcessInstanceByProcessDefId(dto.getProcessModel().getProcessDefId(), new HashMap<String, Object>() {{
//            put("userId", dto.getUserId());
//            put("leaveId", dto.getProcessModel().getLeaveId());
//        }});
//        if(result.getCode() != 200){
//            return ActionResult.error("启动流程失败");
//        }
//
//        /** 设置流程实例ID */
//        String processInstanceId = result.getMessage();
//        dto.getProcessModel().setProcessInstanceId(processInstanceId);
//
//        try {
//            /** 数据入库 */
//            Boolean apply = iLeaveProcessService.apply(dto);
//
//            /** 办理第一个节点：开始 */
//            ActionResult<Boolean> startResult = processEngineMicroService.completeStart(processInstanceId);
//            if(startResult.getCode() != 200){
//                return ActionResult.error("办理开始节点失败");
//            }
//
//            /** 流程节点入库 */
//            LeaveProcessNodeModel nodeModel = new LeaveProcessNodeModel();
////            nodeModel.setComment(dto.get);
//
//
////            return ActionResult.ok();
//
//            return null;
//        } catch (CommonException e) {
//            return ActionResult.error(e.getMessage());
//        }

        return null;

    }


}
