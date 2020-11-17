package cn.i4.data.cloud.system.web.action.activityCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.PartyActivityTypeDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityTypeModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityTypeView;
import cn.i4.data.cloud.core.service.IPartyActivityTypeService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动中心--活动类型的控制层
 * @author wangjc
 * @title: ActivityTypeController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/16-17:16
 */
@RestController
@RequestMapping(value = "/activityCenter/activityType")
public class ActivityTypeController extends WebBaseController {

    private static final String MODULE_NAME = "活动中心--活动类型";
    private static final String KEY_PREFIX = "/activityCenter/activityType";
    @Autowired
    private IPartyActivityTypeService iPartyActivityTypeService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "/加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "activityCenter:activityType/loadTable")
    public ActionResult<IPage<PartyActivityTypeView>> loadTable(PartyActivityTypeDto dto){
        IPage<PartyActivityTypeView> page = iPartyActivityTypeService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 新增
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestLog(module = MODULE_NAME,content = "/新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "activityCenter:activityType/insert")
    public ActionResult<Boolean> insert(@RequestBody PartyActivityTypeDto dto, HttpServletRequest request){
        PartyActivityTypeModel model = dto.getModel();

        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setCreateUserId(this.getUser(request).getId());
        boolean save = iPartyActivityTypeService.save(model);
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
    @RequestLog(module = MODULE_NAME,content = "/修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "activityCenter:activityType/update")
    public ActionResult<Boolean> update(@RequestBody PartyActivityTypeDto dto, HttpServletRequest request){
        PartyActivityTypeModel model = dto.getModel();
        boolean modify = iPartyActivityTypeService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("修改失败");
    }

    /**
     * 删除
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "/删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "activityCenter:activityType/delete")
    public ActionResult<Boolean> delete(PartyActivityTypeDto dto, HttpServletRequest request){
        boolean remove = iPartyActivityTypeService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(true);
        }
        return ActionResult.error("删除失败");
    }

}
