package cn.i4.data.cloud.system.web.action.activityCenter;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.PartyActivityDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityView;
import cn.i4.data.cloud.core.service.IPartyActivityService;
import cn.i4.data.cloud.mongo.core.entity.MongoPartyActivity;
import cn.i4.data.cloud.mongo.core.service.MongoPartyActivityService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动中心--活动发布的控制层
 * @author wangjc
 * @title: ActivityDeployController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/14-14:27
 */
@RestController
@RequestMapping(value = "/activityCenter/activityDeploy")
public class ActivityDeployController extends WebBaseController {

    private static final String MODULE_NAME = "活动中心--活动发布";
    private static final String KEY_PREFIX = "/activityCenter/activityDeploy";
    @Autowired
    private IPartyActivityService iPartyActivityService;
    @Autowired
    private MongoPartyActivityService mongoPartyActivityService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "/加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "activityCenter:activityDeploy/loadTable")
    public ActionResult<IPage<PartyActivityView>> loadTable(PartyActivityDto dto){
        IPage<PartyActivityView> page = iPartyActivityService.selectPage(dto);

        /** 判断过期 */
        for(PartyActivityView view: page.getRecords()){
            if(view.getEndTime() <= System.currentTimeMillis()/1000L){
                view.setStatus(2);
            }
        }
        return ActionResult.ok(page);
    }

    /**
     * 删除，级联删除所有
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "/删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "activityCenter:activityDeploy/delete")
    public ActionResult<Boolean> delete(PartyActivityDto dto){
        try {
            /** 删除mongo */
            mongoPartyActivityService.deleteOne(dto.getMongoId());
            /** 删除MySQL */
            Boolean res = iPartyActivityService.deleteById(dto.getId());
            return ActionResult.ok(res);
        } catch (CommonException e) {
            e.printStackTrace();
            return ActionResult.error("删除失败");
        }
    }

    /**
     * 发布（取消发布）
     * @param dto
     * @return
     */
    @PostMapping(value = "/deploy")
    @RequestLog(module = MODULE_NAME,content = "/发布（取消发布）",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--发布（取消发布）",key = KEY_PREFIX+"/deploy")
    @RequestPermission(value = "activityCenter:activityDeploy/deploy")
    public ActionResult<Boolean> deploy(@RequestBody PartyActivityDto dto){
        PartyActivityModel model = dto.getModel();
        boolean modify = iPartyActivityService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("发布（取消发布）失败");
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
    @RequestPermission(value = "activityCenter:activityDeploy/insert")
    public ActionResult<Boolean> insert(@RequestBody PartyActivityDto dto, HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());
        try {

            /** 富文本的存储mongo */
            MongoPartyActivity mongo = new MongoPartyActivity();
            mongo.setContent(dto.getContent());
            mongo.setMdContent(dto.getMdContent());
            String mongoId = mongoPartyActivityService.insertOne(mongo);

            /** 主数据存储MySQL */
            dto.setMongoId(mongoId);
            Boolean res = iPartyActivityService.insert(dto);
            return ActionResult.ok(res);
        } catch (CommonException e) {
            e.printStackTrace();
            return ActionResult.error("新增失败");
        }
    }

    /**
     * 修改
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "/修改",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--修改",key = KEY_PREFIX+"/update")
    @RequestPermission(value = "activityCenter:activityDeploy/update")
    public ActionResult<Boolean> update(@RequestBody PartyActivityDto dto){
        try {

            /** mongo的修改 */
            MongoPartyActivity mongo = new MongoPartyActivity();
            mongo.setContent(dto.getContent());
            mongo.setMdContent(dto.getMdContent());
            mongo.setMongoId(new ObjectId(dto.getMongoId()));
            mongoPartyActivityService.updateOne(mongo);

            /** MySQL的修改 */
            Boolean res = iPartyActivityService.update(dto);
            return ActionResult.ok(res);
        }catch (CommonException e){
            e.printStackTrace();
            return ActionResult.error("修改失败");
        }
    }

}
