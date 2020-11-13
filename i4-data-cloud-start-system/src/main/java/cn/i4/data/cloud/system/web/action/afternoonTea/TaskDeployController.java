package cn.i4.data.cloud.system.web.action.afternoonTea;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;
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
 * 下午茶--点单任务发布
 * @author wangjc
 * @title: TaskController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/11-11:05
 */
@RestController
@RequestMapping(value = "/afternoonTea/taskDeploy")
public class TaskDeployController extends WebBaseController {

    private static final String MODULE_NAME = "下午茶--点单任务发布";
    private static final String KEY_PREFIX = "/afternoonTea/taskDeploy";
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
    @RequestPermission(value = "afternoonTea:taskDeploy/loadTable")
    public ActionResult<IPage<AfternoonTeaTaskView>> loadTable(AfternoonTeaTaskDto dto){
        IPage<AfternoonTeaTaskView> page = iAfternoonTeaTaskService.selectTaskDeploy(dto);

        /** 判断过期 */
        for(AfternoonTeaTaskView view: page.getRecords()){
            if(view.getEndTime() <= System.currentTimeMillis()/1000L){
                view.setStatus(2);
            }
        }
        return ActionResult.ok(page);
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "afternoonTea:taskDeploy/delete")
    public ActionResult<Boolean> delete(AfternoonTeaTaskDto dto){
        try {
            Boolean delete = iAfternoonTeaTaskService.deleteById(dto.getId());
            return ActionResult.ok(delete);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "afternoonTea:taskDeploy/insert")
    public ActionResult<Boolean> insert(@RequestBody AfternoonTeaTaskDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());
        try {
            Boolean insert = iAfternoonTeaTaskService.insert(dto);
            return ActionResult.ok(insert);
        }catch (CommonException e){
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 修改
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "afternoonTea:taskDeploy/update")
    public ActionResult<Boolean> update(@RequestBody AfternoonTeaTaskDto dto, HttpServletRequest request){
        try {
            Boolean update = iAfternoonTeaTaskService.update(dto);
            return ActionResult.ok(update);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 发布:0未发布，1已发布
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/deploy")
    @RequestLog(module = MODULE_NAME,content = "修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/deploy")
    @RequestPermission(value = "afternoonTea:taskDeploy/deploy")
    public ActionResult<Boolean> deploy(AfternoonTeaTaskDto dto){
        AfternoonTeaTaskModel model = iAfternoonTeaTaskService.getById(dto.getId());

        /** 判断过期 */
        if(model.getEndTime() <= System.currentTimeMillis()/1000L){
            return ActionResult.error("发布失败：截止日期已过");
        }

        model.setStatus(1);
        boolean modify = iAfternoonTeaTaskService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("发布失败");
    }


}
