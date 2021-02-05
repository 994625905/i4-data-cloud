package cn.i4.data.cloud.system.web.action.autoCode;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeDto;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeTableDto;
import cn.i4.data.cloud.core.entity.view.LogAutocodeTableView;
import cn.i4.data.cloud.core.entity.view.LogAutocodeView;
import cn.i4.data.cloud.core.service.ILogAutocodeService;
import cn.i4.data.cloud.core.service.ILogAutocodeTableService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生成代码日志的控制层
 * @author AS065
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/28-10:59
 */
@RequestMapping(value = "/autoCode/codeLog")
@RestController
public class CodeLogController extends WebBaseController {

    private static final String MODULE_NAME = "自动生成代码--生成代码日志";
    private static final String KEY_PREFIX = "/autoCode/codeLog";
    @Autowired
    private ILogAutocodeService iLogAutocodeService;
    @Autowired
    private ILogAutocodeTableService iLogAutocodeTableService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
//    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
//    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
//    @RequestPermission(value = "autoCode:codeLog/loadTable")
    public ActionResult<IPage<LogAutocodeView>> loadTable(LogAutocodeDto dto){
        IPage<LogAutocodeView> page = iLogAutocodeService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 加载日志详情页表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadDetailTable")
    @RequestLog(module = MODULE_NAME,content = "加载日志详情页表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载日志详情页表格",key = KEY_PREFIX+"/loadDetailTable")
    @RequestPermission(value = "autoCode:codeLog/loadDetailTable")
    public ActionResult<IPage<LogAutocodeTableView>> loadDetailTable(LogAutocodeTableDto dto){
        IPage<LogAutocodeTableView> page = iLogAutocodeTableService.selectPage(dto);
        return ActionResult.ok(page);
    }

}
