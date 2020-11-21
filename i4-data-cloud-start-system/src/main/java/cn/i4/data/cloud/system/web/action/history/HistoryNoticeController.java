package cn.i4.data.cloud.system.web.action.history;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeFileView;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeReadView;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeView;
import cn.i4.data.cloud.core.entity.view.UserView;
import cn.i4.data.cloud.core.service.IHistoryNoticeFileService;
import cn.i4.data.cloud.core.service.IHistoryNoticeService;
import cn.i4.data.cloud.mongo.core.entity.MongoHistoryNotice;
import cn.i4.data.cloud.mongo.core.service.MongoHistoryNoticeService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 点滴--历史公告的控制层
 * @author wangjc
 * @title: HistoryNoticeController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/21-15:19
 */
@RequestMapping(value = "/history/historyNotice")
@RestController
public class HistoryNoticeController extends WebBaseController {

    private static final String MODULE_NAME = "点滴--历史公告";
    private static final String KEY_PREFIX = "/history/historyNotice";
    @Autowired
    private IHistoryNoticeService iHistoryNoticeService;
    @Autowired
    private IHistoryNoticeFileService iHistoryNoticeFileService;
    @Autowired
    private MongoHistoryNoticeService mongoHistoryNoticeService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "history:historyNotice/loadTable")
    public ActionResult<IPage<HistoryNoticeView>> loadTable(HistoryNoticeDto dto){
        IPage<HistoryNoticeView> page = iHistoryNoticeService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 发布
     * @param dto
     * @return
     */
    @PostMapping(value = "/deploy")
    @RequestLog(module = MODULE_NAME,content = "发布",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"--发布",key = KEY_PREFIX+"/deploy")
    @RequestPermission(value = "history:historyNotice/deploy")
    public ActionResult<Boolean> deploy(@RequestBody HistoryNoticeDto dto){
        HistoryNoticeModel model = dto.getModel();
        model.setStatus(1);
        model.setDeployTime(System.currentTimeMillis()/1000L);

        boolean modify = iHistoryNoticeService.modify(model);
        if(modify){
            return ActionResult.ok(true);
        }
        return ActionResult.error("发布失败");
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    @PostMapping(value = "/delete")
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "history:historyNotice/delete")
    public ActionResult<Boolean> delete(HistoryNoticeDto dto){

        /** 删除redis缓存 */
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.HISTORY_NOTICE_READ_USER,dto.getId().toString());

        /** 删除mongo */
        Long deleteMongo = mongoHistoryNoticeService.deleteOne(dto.getMongoId());
        if(deleteMongo < 1){
            return ActionResult.error("删除mongo失败");
        }

        /** 删除MySQL */
        try {
            Boolean delete = iHistoryNoticeService.deleteById(dto.getId());
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
    @RequestLog(module = MODULE_NAME,content = "新增",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"--新增",key = KEY_PREFIX+"/insert")
    @RequestPermission(value = "history:historyNotice/insert")
    public ActionResult<Boolean> insert(@RequestBody HistoryNoticeDto dto, HttpServletRequest request){

        /** 新增mongo */
        MongoHistoryNotice mongo = new MongoHistoryNotice();
        mongo.setContent(dto.getContent());
        mongo.setMdContent(dto.getMdContent());
        String mongoId = mongoHistoryNoticeService.insertOne(mongo);

        /** 新增MySQL */
        dto.setUserId(this.getUser(request).getId());
        dto.setMongoId(mongoId);
        try {
            Boolean insert = iHistoryNoticeService.insert(dto);
            return ActionResult.ok(insert);
        } catch (CommonException e) {
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
    @RequestPermission(value = "history:historyNotice/update")
    public ActionResult<Boolean> update(@RequestBody HistoryNoticeDto dto,HttpServletRequest request){
        dto.setUserId(this.getUser(request).getId());

        /** 修改mongo */
        MongoHistoryNotice mongo = new MongoHistoryNotice();
        mongo.setContent(dto.getContent());
        mongo.setMdContent(dto.getMdContent());
        mongo.setMongoId(new ObjectId(dto.getMongoId()));
        mongoHistoryNoticeService.updateOne(mongo);

        /** 修改MySQL */
        try {
            Boolean update = iHistoryNoticeService.update(dto);
            return ActionResult.ok(update);
        } catch (CommonException e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * 查询附件列表
     * @param dto
     * @return
     */
    @PostMapping(value = "/getFileListByWeekReportId")
    @RequestLog(module = MODULE_NAME,content = "查询附件列表",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--查询附件列表",key = KEY_PREFIX+"/getFileListByWeekReportId")
    @RequestPermission(value = "history:historyNotice/getFileListByWeekReportId")
    public ActionResult<List<HistoryNoticeFileView>> getFileListByWeekReportId(HistoryNoticeDto dto){
        List<HistoryNoticeFileView> list = iHistoryNoticeFileService.selectByNoticeId(dto.getId());
        return ActionResult.ok(list);
    }

    /**
     * 查询已读人员，从MySQL和redis分别获取
     * @param dto
     * @return
     */
    @PostMapping(value = "/selectReadUser")
    @RequestLog(module = MODULE_NAME,content = "查询已读人员",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--查询已读人员",key = KEY_PREFIX+"/selectReadUser")
    @RequestPermission(value = "history:historyNotice/selectReadUser")
    public ActionResult<List<UserView>> selectReadUser(HistoryNoticeDto dto){
        /** 从redis获取一部分userId */
        Set<Integer> userIdList = (Set<Integer>) redisService.hget(RedisConstant.HASH_KEY.HISTORY_NOTICE_READ_USER,dto.getId().toString());

        List<UserView> list = iUserService.selectByHistoryNoticeId(dto.getId(), userIdList);
        return ActionResult.ok(list);
    }

}
