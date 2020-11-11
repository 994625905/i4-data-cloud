package cn.i4.data.cloud.system.web.action.afternoonTea;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;
import cn.i4.data.cloud.core.service.IAfternoonTeaTaskService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 下午茶--点单任务
 * @author wangjc
 * @title: TaskController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/11-11:05
 */
@RestController
@RequestMapping(value = "/afternoonTea/task")
public class TaskController extends WebBaseController {

    private static final String MODULE_NAME = "下午茶--点单任务";
    private static final String KEY_PREFIX = "/afternoonTea/task";
    @Autowired
    private IAfternoonTeaTaskService iAfternoonTeaTaskService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "afternoonTea:task/loadTable")
    public ActionResult<IPage<AfternoonTeaTaskView>> loadTable(AfternoonTeaTaskDto dto){
        IPage<AfternoonTeaTaskView> page = iAfternoonTeaTaskService.selectPage(dto);
        return ActionResult.ok(page);
    }

}
