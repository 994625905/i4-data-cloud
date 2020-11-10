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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 日志捕获--请求日志的控制层（针对当前用户）
 * @author wangjc
 * @title: RequestController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/10-9:59
 */
@RestController
@RequestMapping(value = "/logCache/request")
public class RequestController extends WebBaseController {

    private static final String MODULE_NAME = "日志捕获--请求日志";
    private static final String KEY_PREFIX = "/logCache/request";
    @Autowired
    private ILogRequestService iLogRequestService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestPermission(value = "logCache:request/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    public ActionResult<List<Map<String,Object>>> loadTable(LogRequestDto dto, HttpServletRequest request){
        dto.setUserId(getUser(request).getId());
        List<Map<String,Object>> page = iLogRequestService.selectByUserId(dto);
        return ActionResult.ok(page);
    }

    /**
     * 加载详情
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/loadDetail")
    @RequestPermission(value = "logCache:request/loadDetail")
    @RequestLog(module = MODULE_NAME,content = "加载详情",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载详情",key = KEY_PREFIX+"/loadDetail")
    public ActionResult<List<LogRequestView>> loadDetail(LogRequestDto dto, HttpServletRequest request){
        dto.setUserId(getUser(request).getId());
        List<LogRequestView> list = iLogRequestService.selectByUserIdAndDate(dto);
        return ActionResult.ok(list);
    }

}
