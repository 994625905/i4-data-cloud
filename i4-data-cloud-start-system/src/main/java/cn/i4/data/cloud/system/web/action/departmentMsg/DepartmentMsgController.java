package cn.i4.data.cloud.system.web.action.departmentMsg;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.DepartmentDto;
import cn.i4.data.cloud.core.entity.model.DepartmentModel;
import cn.i4.data.cloud.core.entity.view.DepartmentView;
import cn.i4.data.cloud.core.service.IDepartmentService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统管理--部门管理的控制层
 * @author wangjc
 * @title: DepartmentMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/31-15:18
 */
@RequestMapping(value = "/systemMsg/departmentMsg")
@RestController
public class DepartmentMsgController extends WebBaseController {

    private static final String MODULE_NAME = "系统管理--部门管理";
    private static final String KEY_PREFIX = "/systemMsg/departmentMsg";

    @Autowired
    private IDepartmentService iDepartmentService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    public ActionResult<IPage<DepartmentView>> loadTable(DepartmentDto dto){

        IPage<DepartmentView> page = iDepartmentService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除部门
     * @param dto
     * @return
     */
    @PostMapping(value = "/deleteById")
    @RequestLog(module = MODULE_NAME,content = "删除部门",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除部门",key = KEY_PREFIX+"/deleteById")
    public ActionResult<Boolean> deleteById(DepartmentDto dto){

        boolean remove = iDepartmentService.removeById(dto.getId());
        if(!remove){
            return ActionResult.error("删除失败");
        }
        return ActionResult.ok(true);
    }

    /**
     * 新增部门
     * @param dto
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增部门",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增部门",key = KEY_PREFIX+"/insert")
    public ActionResult<Boolean> insert(DepartmentDto dto){
        DepartmentModel model = dto.getModel();
        model.setCreateTime(System.currentTimeMillis()/1000L);

        boolean save = iDepartmentService.save(model);
        if(!save){
            return ActionResult.error("新增失败");
        }
        return ActionResult.ok(true);
    }

    /**
     * 修改部门
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改部门",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改部门",key = KEY_PREFIX+"/update")
    public ActionResult<Boolean> update(DepartmentDto dto){
        DepartmentModel model = dto.getModel();
        model.setUpdateTime(System.currentTimeMillis()/1000L);

        boolean modify = iDepartmentService.modify(model);
        if(!modify){
            return ActionResult.error("修改失败");
        }
        return ActionResult.ok(true);
    }

}
