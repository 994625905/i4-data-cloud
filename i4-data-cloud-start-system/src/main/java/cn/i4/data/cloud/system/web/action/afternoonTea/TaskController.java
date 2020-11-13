package cn.i4.data.cloud.system.web.action.afternoonTea;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaSelectDto;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;
import cn.i4.data.cloud.core.service.IAfternoonTeaSelectService;
import cn.i4.data.cloud.core.service.IAfternoonTeaTaskService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 下午茶--点单任务（已发布，供点单）
 * @author wangjc
 * @title: TaskController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/12-16:41
 */
@RestController
@RequestMapping(value = "/afternoonTea/task")
public class TaskController extends WebBaseController {

    private static final String MODULE_NAME = "下午茶--点单任务（已发布）";
    private static final String KEY_PREFIX = "/afternoonTea/task";
    @Autowired
    private IAfternoonTeaTaskService iAfternoonTeaTaskService;
    @Autowired
    private IAfternoonTeaSelectService iAfternoonTeaSelectService;

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

        /** 判断过期 */
        for(AfternoonTeaTaskView view: page.getRecords()){
            if(view.getEndTime() <= System.currentTimeMillis()/1000L){
                view.setStatus(2);
            }
        }
        return ActionResult.ok(page);
    }

    /**
     * 点单
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/order")
    @RequestLog(module = MODULE_NAME,content = "点单",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--点单",key = KEY_PREFIX+"/order")
    @RequestPermission(value = "afternoonTea:task/order")
    public ActionResult<Boolean> order(@RequestBody AfternoonTeaSelectDto dto, HttpServletRequest request) {
        dto.setUserId(this.getUser(request).getId());
        try {
            /** 点单截止时间验证 */
            AfternoonTeaTaskModel taskModel = iAfternoonTeaTaskService.getById(dto.getSelectList().get(0).getTeaTaskId());
            if(taskModel.getEndTime() <= System.currentTimeMillis()/1000L){
                return ActionResult.error("点单失败：截止时间已过");
            }
            /** 点单 */
            Boolean res = iAfternoonTeaSelectService.order(dto);
            return ActionResult.ok(res);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 加载详情页的表格
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/detailTable")
    @RequestLog(module = MODULE_NAME,content = "加载详情页的表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载详情页的表格",key = KEY_PREFIX+"/detailTable")
    @RequestPermission(value = "afternoonTea:task/detailTable")
    public ActionResult<IPage<AfternoonTeaSelectView>> detailTable(AfternoonTeaSelectDto dto,HttpServletRequest request){
        IPage<AfternoonTeaSelectView> page = iAfternoonTeaSelectService.selectDetailTable(dto);
        return ActionResult.ok(page);
    }

}
