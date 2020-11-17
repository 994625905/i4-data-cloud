package cn.i4.data.cloud.system.web.action.activityCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.PartyActivityDto;
import cn.i4.data.cloud.core.entity.view.PartyActivityView;
import cn.i4.data.cloud.core.service.IPartyActivityService;
import cn.i4.data.cloud.mongo.core.service.MongoPartyActivityService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


}
