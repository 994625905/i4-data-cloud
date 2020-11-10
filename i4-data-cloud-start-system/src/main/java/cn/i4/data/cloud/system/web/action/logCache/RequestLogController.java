package cn.i4.data.cloud.system.web.action.logCache;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.LogRequestDto;
import cn.i4.data.cloud.core.entity.view.LogRequestView;
import cn.i4.data.cloud.core.service.ILogRequestService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志捕获--请求日志汇总的控制层
 * @author wangjc
 * @title: RequestLogController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/9-18:08
 */
@RestController
@RequestMapping(value = "/logCache/requestLog")
public class RequestLogController extends WebBaseController {

    private static final String MODULE_NAME = "日志捕获--请求日志汇总";
    private static final String KEY_PREFIX = "/logCache/requestLog";
    @Autowired
    private ILogRequestService iLogRequestService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestPermission(value = "logCache:requestLog/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    public ActionResult<IPage<LogRequestView>> loadTable(LogRequestDto dto){
        IPage<LogRequestView> page = iLogRequestService.selectPage(dto);
        return ActionResult.ok(page);
    }

}
