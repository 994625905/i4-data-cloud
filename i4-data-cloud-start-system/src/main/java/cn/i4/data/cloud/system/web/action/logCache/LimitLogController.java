package cn.i4.data.cloud.system.web.action.logCache;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.LogLimitDto;
import cn.i4.data.cloud.core.entity.view.LogLimitView;
import cn.i4.data.cloud.core.service.ILogLimitService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志捕获--限流日志汇总的控制层
 * @author wangjc
 * @title: LimitLogController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/9-19:00
 */
@RestController
@RequestMapping(value = "/logCache/limitLog")
public class LimitLogController extends WebBaseController {

    private static final String MODULE_NAME = "日志捕获--限流日志汇总";
    private static final String KEY_PREFIX = "/logCache/limitLog";
    @Autowired
    private ILogLimitService iLogLimitService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "logCache:limitLog/loadTable")
    public ActionResult<IPage<LogLimitView>> loadTable(LogLimitDto dto){
        IPage<LogLimitView> page = iLogLimitService.selectPage(dto);
        return ActionResult.ok(page);
    }


}
