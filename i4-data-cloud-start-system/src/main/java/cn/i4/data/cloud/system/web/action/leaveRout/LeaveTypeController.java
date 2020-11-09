package cn.i4.data.cloud.system.web.action.leaveRout;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.LeaveTypeDto;
import cn.i4.data.cloud.core.entity.model.LeaveTypeModel;
import cn.i4.data.cloud.core.entity.view.LeaveTypeView;
import cn.i4.data.cloud.core.service.ILeaveTypeService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 请假事务--请假类型的控制层
 * @author wangjc
 * @title: LeaveTypeController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/1-15:05
 */
@RequestMapping(value = "/leaveRout/leaveType")
@RestController
public class LeaveTypeController extends WebBaseController {

    private static final String MODULE_NAME = "请假事务--请假类型";
    private static final String KEY_PREFIX = "/leaveRout/leaveType";

    @Autowired
    private ILeaveTypeService iLeaveTypeService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestPermission(value = "leaveRout:leaveType/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    public ActionResult<IPage<LeaveTypeView>> loadTable(LeaveTypeDto dto){

        IPage<LeaveTypeView> page = iLeaveTypeService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestPermission(value = "leaveRout:leaveType/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    public ActionResult<Boolean> delete(LeaveTypeDto dto){

        boolean remove = iLeaveTypeService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(remove);
        }
        return ActionResult.error("删除类型失败");
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestPermission(value = "leaveRout:leaveType/insert")
    @RequestLog(module = MODULE_NAME,content = "新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    public ActionResult<Boolean> insert(LeaveTypeDto dto, HttpServletRequest request){
        LeaveTypeModel model = new LeaveTypeModel();
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setUserId(getUser(request).getId());
        model.setName(dto.getName());

        boolean save = iLeaveTypeService.save(model);
        if(save){
            return ActionResult.ok(save);
        }
        return ActionResult.error("新增失败");
    }

    /**
     * 编辑
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    @RequestPermission(value = "leaveRout:leaveType/update")
    @RequestLog(module = MODULE_NAME,content = "编辑",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--编辑",key = KEY_PREFIX+"/update")
    public ActionResult<Boolean> update(LeaveTypeDto dto,HttpServletRequest request){
        LeaveTypeModel model = iLeaveTypeService.getById(dto.getId());
        model.setName(dto.getName());
        model.setUserId(getUser(request).getId());

        boolean modify = iLeaveTypeService.modify(model);
        if(modify){
            return ActionResult.ok(modify);
        }
        return ActionResult.error("编辑失败");
    }

}
