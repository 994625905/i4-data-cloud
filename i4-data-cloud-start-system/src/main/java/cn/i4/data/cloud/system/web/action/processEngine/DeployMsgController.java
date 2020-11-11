package cn.i4.data.cloud.system.web.action.processEngine;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.VActReDeployProcdefDto;
import cn.i4.data.cloud.core.entity.view.VActReDeployProcdefView;
import cn.i4.data.cloud.core.service.IVActReDeployProcdefService;
import cn.i4.data.cloud.system.microservice.ProcessEngineMicroService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程引擎--流程部署的控制层
 * @author wangjc
 * @title: DeployMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/30-17:57
 */
@RequestMapping(value = "/processEngine/deployMsg")
@RestController
public class DeployMsgController extends WebBaseController {

    private static final String KEY_PREFIX = "/processEngine/deployMsg";
    private static final String MODULE_NAME = "流程引擎--流程部署";

    @Autowired
    private IVActReDeployProcdefService ivActReDeployProcdefService;
    @Autowired
    private ProcessEngineMicroService processEngineMicroService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"loadTable")
    @RequestPermission(value = "processEngine:deployMsg/loadTable")
    public ActionResult<IPage<VActReDeployProcdefView>> loadTable(VActReDeployProcdefDto dto){
        IPage<VActReDeployProcdefView> page = ivActReDeployProcdefService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除流程定义，级联删除
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除流程定义",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--删除流程定义",key = KEY_PREFIX+"delete")
    @RequestPermission(value = "processEngine:deployMsg/delete")
    public ActionResult<Boolean> delete(VActReDeployProcdefDto dto){
        ActionResult<Boolean> res = processEngineMicroService.deleteProcessDeploymentById(dto.getDeploymentId(), true);
        return res;
    }

}
