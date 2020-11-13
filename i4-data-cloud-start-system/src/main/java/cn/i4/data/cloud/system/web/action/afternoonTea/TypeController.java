package cn.i4.data.cloud.system.web.action.afternoonTea;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTypeDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTypeModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTypeView;
import cn.i4.data.cloud.core.service.IAfternoonTeaTypeService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 下午茶--类型管理
 * @author wangjc
 * @title: TypeController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/13-14:01
 */
@RequestMapping(value = "/afternoonTea/type")
@RestController
public class TypeController extends WebBaseController {

    private static final String MODULE_NAME = "下午茶--类型管理";
    private static final String KEY_PREFIX = "/afternoonTea/type";
    @Autowired
    private IAfternoonTeaTypeService iAfternoonTeaTypeService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "afternoonTea:type/loadTable")
    public ActionResult<IPage<AfternoonTeaTypeView>> loadTable(AfternoonTeaTypeDto dto){
        IPage<AfternoonTeaTypeView> page = iAfternoonTeaTypeService.selectPage(dto);
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
    @RequestPermission(value = "afternoonTea:type/delete")
    public ActionResult<Boolean> delete(AfternoonTeaTypeDto dto){
        boolean remove = iAfternoonTeaTypeService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(true);
        }
        return ActionResult.error("删除失败");
    }

    /**
     * 新增
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "afternoonTea:type/insert")
    public ActionResult<Boolean> insert(@RequestBody AfternoonTeaTypeDto dto, HttpServletRequest request){
        AfternoonTeaTypeModel model = dto.getModel();
        model.setCreateUserId(this.getUser(request).getId());
        model.setCreateTime(System.currentTimeMillis()/1000L);
        boolean save = iAfternoonTeaTypeService.save(model);
        if(save){
            return ActionResult.ok(true);
        }
        return ActionResult.error("新增失败");
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
    @RequestPermission(value = "afternoonTea:type/update")
    public ActionResult<Boolean> update(@RequestBody AfternoonTeaTypeDto dto,HttpServletRequest request){
        AfternoonTeaTypeModel model = dto.getModel();
        boolean modify = iAfternoonTeaTypeService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("修改失败");
    }

}
