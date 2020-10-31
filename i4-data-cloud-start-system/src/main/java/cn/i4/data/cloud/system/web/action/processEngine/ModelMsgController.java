package cn.i4.data.cloud.system.web.action.processEngine;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.VActReModelDto;
import cn.i4.data.cloud.core.entity.view.VActReModelView;
import cn.i4.data.cloud.core.service.IVActReModelService;
import cn.i4.data.cloud.system.microservice.ProcessEngineMicroService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程引擎--模板管理的控制层
 * @author wangjc
 * @title: ModelMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/30-17:27
 */
@RequestMapping(value = "/processEngine/modelMsg")
@RestController
public class ModelMsgController extends WebBaseController {

    private static final String KEY_PREFIX = "/processEngine/modelMsg";
    private static final String MODULE_NAME = "流程引擎--模板管理";

    @Autowired
    private IVActReModelService ivActReModelService;
    @Autowired
    private ProcessEngineMicroService processEngineMicroService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"加载表格",key = KEY_PREFIX+"loadTable")
    public ActionResult<IPage<VActReModelView>> loadTable(VActReModelDto dto){

        IPage<VActReModelView> page = ivActReModelService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除流程模板
     * @param dto
     * @return
     */
    @PostMapping(value = "/deleteById")
    @RequestLog(module = MODULE_NAME,content = "删除流程模板",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"删除流程模板",key = KEY_PREFIX+"deleteById")
    public ActionResult<Boolean> deleteById(VActReModelDto dto){

        ActionResult<Boolean> result = processEngineMicroService.deleteProcessModelById(dto.getModelId());
        return result;
    }

    /**
     * 部署流程模板
     * @param dto
     * @return
     */
    @PostMapping(value = "/deploy")
    @RequestLog(module = MODULE_NAME,content = "部署流程模板",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"部署流程模板",key = KEY_PREFIX+"deploy")
    public ActionResult<String> deploy(VActReModelDto dto){

        ActionResult<String> res = processEngineMicroService.deployProcessModelById(dto.getModelId());
        return res;
    }


}
