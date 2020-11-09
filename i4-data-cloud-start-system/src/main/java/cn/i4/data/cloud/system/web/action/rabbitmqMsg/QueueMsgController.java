package cn.i4.data.cloud.system.web.action.rabbitmqMsg;

import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqQueueDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqQueueView;
import cn.i4.data.cloud.core.service.ISetRabbitmqQueueService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 消息队列--队列管理的控制层
 * @author wangjc
 * @title: QueueMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/24-18:39
 */
@RequestMapping(value = "/rabbitmqMsg/queueMsg")
@RestController
public class QueueMsgController extends WebBaseController {

    private final static String MODULE_NAME = "消息队列--队列管理";
    @Autowired
    private ISetRabbitmqQueueService iSetRabbitmqQueueService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestPermission(value = "rabbitmqMsg:queueMsg/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载队列表格",type = RequestType.SELECT)
    public ActionResult<IPage<SetRabbitmqQueueView>> loadTable(SetRabbitmqQueueDto dto){

        IPage<SetRabbitmqQueueView> res = iSetRabbitmqQueueService.selectPage(dto);
        return ActionResult.ok(res);
    }

    /**
     * 刷新Redis缓存
     * @return
     */
    @PostMapping(value = "/refreshCache")
    @RequestPermission(value = "rabbitmqMsg:queueMsg/refreshCache")
    @RequestLog(module = MODULE_NAME,content = "刷新Redis缓存队列",type = RequestType.UPDATE)
    public ActionResult<Boolean> refreshCache(){

        List<SetRabbitmqQueueModel> list = iSetRabbitmqQueueService.list();
        redisService.set(RedisConstant.KEY.RABBITMQ_QUEUE, list);
        return ActionResult.ok(true);
    }

    /**
     * 根据ID删除
     * @param dto
     * @return
     */
    @PostMapping(value = "/deleteById")
    @RequestPermission(value = "rabbitmqMsg:queueMsg/deleteById")
    @RequestLog(module = MODULE_NAME,content = "根据ID删除队列",type = RequestType.DELETE)
    public ActionResult<Boolean> deleteById(SetRabbitmqQueueDto dto){

        boolean remove = iSetRabbitmqQueueService.removeById(dto.getId());
        if(!remove){
            return ActionResult.error("根据ID删除队列失败");
        }
        /** 更新Redis缓存 */
        this.refreshCache();
        return ActionResult.ok(remove);
    }

    /**
     * 新增队列
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/insert")
    @RequestPermission(value = "rabbitmqMsg:queueMsg/insert")
    @RequestLog(module = MODULE_NAME,content = "新增队列",type = RequestType.INSERT)
    public ActionResult<Boolean> insert(@RequestBody SetRabbitmqQueueDto dto, HttpServletRequest request){
        SetRabbitmqQueueModel model = dto.getModel();
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setUserId(this.getUser(request).getId());

        /** 新增 */
        boolean save = iSetRabbitmqQueueService.save(model);
        if(!save){
            return ActionResult.error("新增队列失败");
        }
        /** 更新Redis缓存 */
        this.refreshCache();
        return ActionResult.ok(save);
    }

    /**
     * 修改队列
     * @param dto
     * @return
     */
    @PostMapping(value = "/update")
    @RequestPermission(value = "rabbitmqMsg:queueMsg/update")
    @RequestLog(module = MODULE_NAME,content = "修改队列",type = RequestType.UPDATE)
    public ActionResult<Boolean> update(@RequestBody SetRabbitmqQueueDto dto){
        SetRabbitmqQueueModel model = dto.getModel();
        model.setUpdateTime(System.currentTimeMillis()/1000L);

        /** 修改 */
        boolean modify = iSetRabbitmqQueueService.modify(model);
        if(!modify){
            return ActionResult.error("修改队列失败");
        }
        /** 更新缓存 */
        this.refreshCache();
        return ActionResult.ok(modify);
    }


}
