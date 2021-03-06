package cn.i4.data.cloud.system.web.action.weekReport;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.WeekreportDto;
import cn.i4.data.cloud.core.entity.view.WeekreportFileView;
import cn.i4.data.cloud.core.entity.view.WeekreportView;
import cn.i4.data.cloud.core.service.IWeekreportFileService;
import cn.i4.data.cloud.core.service.IWeekreportService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 周报事务--周报日志的控制层
 * @author wangjc
 * @title: WeekReportLogController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/7-15:37
 */
@RequestMapping(value = "/weekReport/weekReportLog")
@RestController
public class WeekReportLogController extends WebBaseController {

    private static final String MODULE_NAME = "周报事务--周报日志";
    private static final String KEY_PREFIX = "/weekReport/weekReportLog";

    @Autowired
    private IWeekreportService iWeekreportService;
    @Autowired
    private IWeekreportFileService iWeekreportFileService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "weekReport:weekReportLog/loadTable")
    public ActionResult<IPage<WeekreportView>> loadTable(WeekreportDto dto){

        IPage<WeekreportView> page = iWeekreportService.selectAll(dto);
        return ActionResult.ok(page);
    }

    /**
     * 查询附件列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getFileListByWeekReportId")
    @RequestLog(module = MODULE_NAME,content = "查询附件列表",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--查询附件列表",key = KEY_PREFIX+"/getFileListByWeekReportId")
    @RequestPermission(value = "weekReport:weekReportLog/getFileListByWeekReportId")
    public ActionResult<List<WeekreportFileView>> getFileListByWeekReportId(WeekreportDto dto){
        List<WeekreportFileView> list = iWeekreportFileService.selectByWeekReportId(dto.getId());
        return ActionResult.ok(list);
    }

}
