package cn.i4.data.cloud.system.web.action.leaveRout;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.LeaveDto;
import cn.i4.data.cloud.core.entity.view.LeaveView;
import cn.i4.data.cloud.core.service.ILeaveProcessService;
import cn.i4.data.cloud.core.service.ILeaveService;
import cn.i4.data.cloud.system.microservice.ProcessEngineMicroService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请假业务--请假记录的控制层
 * @author wangjc
 * @title: LeaveLogController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/3-19:45
 */
@RequestMapping(value = "/leaveRout/leaveLog")
@RestController
public class LeaveLogController extends WebBaseController {

    private static final String MODULE_NAME = "请假事务--请假记录";
    private static final String KEY_PREFIX = "/leaveRout/leaveLog";
    @Autowired
    private ILeaveService iLeaveService;
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
    public ActionResult<IPage<LeaveView>> loadTable(LeaveDto dto){

        IPage<LeaveView> page = iLeaveService.selectAllLog(dto);
        return ActionResult.ok(page);
    }


}
