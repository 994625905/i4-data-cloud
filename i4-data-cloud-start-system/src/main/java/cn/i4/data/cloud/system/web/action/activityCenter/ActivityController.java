package cn.i4.data.cloud.system.web.action.activityCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.PartyActivityDto;
import cn.i4.data.cloud.core.entity.dto.PartyActivitySignDto;
import cn.i4.data.cloud.core.entity.model.PartyActivitySignModel;
import cn.i4.data.cloud.core.entity.view.PartyActivitySignView;
import cn.i4.data.cloud.core.entity.view.PartyActivityView;
import cn.i4.data.cloud.core.entity.view.UserView;
import cn.i4.data.cloud.core.service.IPartyActivityService;
import cn.i4.data.cloud.core.service.IPartyActivitySignService;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.mongo.core.service.MongoPartyActivityService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动中心--活动行的控制层
 * @author wangjc
 * @title: ActivityController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/16-19:53
 */
@RestController
@RequestMapping(value = "/activityCenter/activity")
public class ActivityController extends WebBaseController {

    private static final String MODULE_NAME = "活动中心--活动行";
    private static final String KEY_PREFIX = "/activityCenter/activity";
    @Autowired
    private IPartyActivityService iPartyActivityService;
    @Autowired
    private IPartyActivitySignService iPartyActivitySignService;
    @Autowired
    private MongoPartyActivityService mongoPartyActivityService;

    /**
     * 加载表格
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "/加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "activityCenter:activity/loadTable")
    public ActionResult<IPage<PartyActivityView>> loadTable(PartyActivityDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());
        IPage<PartyActivityView> page = iPartyActivityService.selectActivity(dto);

        /** 判断过期 */
        for(PartyActivityView view: page.getRecords()){
            if(view.getEndTime() <= System.currentTimeMillis()/1000L){
                view.setStatus(2);
            }
        }
        return ActionResult.ok(page);
    }

    /**
     * 签到
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/sign")
    @RequestLog(module = MODULE_NAME,content = "/签到",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--签到",key = KEY_PREFIX+"/sign")
    @RequestPermission(value = "activityCenter:activity/sign")
    public ActionResult<Boolean> sign(@RequestBody PartyActivitySignDto dto, HttpServletRequest request){
        PartyActivitySignModel model = dto.getModel();

        /** 签到时间判断 */
        if(dto.getSignStartTime() > System.currentTimeMillis()/1000L){
            return ActionResult.error("签到还未开始");
        }
        if(dto.getSignEndTime() < System.currentTimeMillis()/1000L){
            return ActionResult.error("签到已经结束");
        }
        model.setSignTime(System.currentTimeMillis()/1000L);
        model.setSignUserId(this.getUser(request).getId());
        boolean save = iPartyActivitySignService.save(model);
        if(save){
            return ActionResult.ok(save);
        }
        return ActionResult.error("签到失败");
    }

    /**
     * 加载签到表格
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/loadSignTable")
    @RequestLog(module = MODULE_NAME,content = "/加载签到表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载签到表格",key = KEY_PREFIX+"/loadSignTable")
    @RequestPermission(value = "activityCenter:activity/loadSignTable")
    public ActionResult<IPage<PartyActivitySignView>> loadSignTable(PartyActivitySignDto dto,HttpServletRequest request){
        IPage<PartyActivitySignView> page = iPartyActivitySignService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 加载未签到列表
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/noSignList")
    @RequestLog(module = MODULE_NAME,content = "/加载未签到列表",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载未签到列表",key = KEY_PREFIX+"/noSignList")
    @RequestPermission(value = "activityCenter:activity/noSignList")
    public ActionResult<List<UserView>> noSignList(PartyActivitySignDto dto,HttpServletRequest request){
        List<UserView> list = iUserService.selectActivityNoSignList(dto.getActivityId());
        return ActionResult.ok(list);
    }

    /**
     * 修改签到
     * @param dto
     * @return
     */
    @PostMapping(value = "/updateSign")
    @RequestLog(module = MODULE_NAME,content = "/修改签到",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改签到",key = KEY_PREFIX+"/updateSign")
    @RequestPermission(value = "activityCenter:activity/updateSign")
    public ActionResult<Boolean> updateSign(@RequestBody PartyActivitySignDto dto){
        boolean modify = iPartyActivitySignService.modify(dto.getModel());
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("修改失败");
    }


}
