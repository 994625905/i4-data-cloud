package cn.i4.data.cloud.system.web.action.leaveRout;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.SystemConstant;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.LeaveDto;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessDto;
import cn.i4.data.cloud.core.entity.model.LeaveModel;
import cn.i4.data.cloud.core.entity.model.LeaveProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.LeaveFileView;
import cn.i4.data.cloud.core.entity.view.LeaveView;
import cn.i4.data.cloud.core.service.ILeaveFileService;
import cn.i4.data.cloud.core.service.ILeaveProcessService;
import cn.i4.data.cloud.core.service.ILeaveService;
import cn.i4.data.cloud.system.microservice.ProcessEngineMicroService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 请假事务--请假的控制层
 * @author wangjc
 * @title: leaveApplyController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/1-17:41
 */
@RestController
@RequestMapping(value = "/leaveRout/leaveApply")
public class LeaveApplyController extends WebBaseController {

    private static final String MODULE_NAME = "请假事务--请假申请";
    private static final String KEY_PREFIX = "/leaveRout/leaveApply";
    @Autowired
    private ILeaveService iLeaveService;
    @Autowired
    private ILeaveFileService iLeaveFileService;
    @Autowired
    private ProcessEngineMicroService processEngineMicroService;
    @Autowired
    private ILeaveProcessService iLeaveProcessService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "leaveRout:leaveApply/loadTable")
    public ActionResult<IPage<LeaveView>> loadTable(LeaveDto dto,HttpServletRequest request){
        dto.setUserId(getUser(request).getId());
        IPage<LeaveView> page = iLeaveService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 加载明确条件页表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadWhereTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadWhereTable")
    @RequestPermission(value = "leaveRout:leaveApply/loadWhereTable")
    public ActionResult<IPage<LeaveView>> loadWhereTable(LeaveDto dto){
        IPage<LeaveView> page = iLeaveService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 新增请假
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增请假",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增请假",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "leaveRout:leaveApply/insert")
    public ActionResult<Boolean> insert(@RequestBody LeaveDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());
        try {
            Boolean insert = iLeaveService.insert(dto);
            return ActionResult.ok(true);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 删除请假
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "leaveRout:leaveApply/delete")
    public ActionResult<Boolean> delete(LeaveDto dto){

        try {
            boolean delete = iLeaveService.deleteByLeaveId(dto.getId());
            return ActionResult.ok(delete);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 修改请假
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改请假",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改请假",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "leaveRout:leaveApply/update")
    public ActionResult<Boolean> update(@RequestBody LeaveDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());
        try {
            boolean update = iLeaveService.update(dto);
            return ActionResult.ok(update);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 查询请假条附件列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getFileListByLeaveId")
    @RequestLog(module = MODULE_NAME,content = "查询请假条附件列表",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--查询请假条附件列表",key = KEY_PREFIX+"/getFileListByLeaveId")
    @RequestPermission(value = "leaveRout:leaveApply/getFileListByLeaveId")
    public ActionResult<List<LeaveFileView>> getFileListByLeaveId(LeaveDto dto){
        List<LeaveFileView> list = iLeaveFileService.selectByLeaveId(dto.getId());
        return ActionResult.ok(list);
    }

    /**
     * 发送请假申请
     * @return
     */
    @PostMapping(value = "/apply")
    @RequestLog(module = MODULE_NAME,content = "发送请假申请",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--发送请假申请",key = KEY_PREFIX+"/apply")
    @RequestPermission(value = "leaveRout:leaveApply/apply")
    public ActionResult<Boolean> apply(@RequestBody LeaveProcessDto dto, HttpServletRequest request){
        dto.setUserId(getUser(request).getId());

        /** 启动一个流程，流程变量设置为当前用户和假条ID */
        ActionResult<String> result = processEngineMicroService.startProcessInstanceByProcessDefId(dto.getProcessModel().getProcessDefId(), new HashMap<String, Object>() {{
            put("userId", dto.getUserId());
            put("leaveId", dto.getProcessModel().getLeaveId());
        }});
        if(result.getCode() != 200){
            return ActionResult.error("启动流程失败");
        }

        /** 设置流程实例ID */
        String processInstanceId = result.getMessage();
        dto.getProcessModel().setProcessInstanceId(processInstanceId);

        try {
            /** 数据入库 */
            Boolean apply = iLeaveProcessService.apply(dto);

            /** 办理第一个节点：开始 */
            ActionResult<Boolean> startResult = processEngineMicroService.completeStart(processInstanceId);
            if(startResult.getCode() != 200){
                return ActionResult.error("办理开始节点失败");
            }

            /** 流程节点入库 */
            LeaveProcessNodeModel nodeModel = new LeaveProcessNodeModel();
//            nodeModel.setComment(dto.get);


//            return ActionResult.ok();

            return null;
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }

    }

}
