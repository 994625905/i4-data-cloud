package cn.i4.data.cloud.system.web.action.rabbitmqMsg;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqExchangeDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqExchangeModel;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqExchangeView;
import cn.i4.data.cloud.core.service.ISetRabbitmqExchangeService;
import cn.i4.data.cloud.core.service.ISetRabbitmqQueueService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 交换机管理
 * @author wangjc
 * @title: SettingMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/24-14:52
 */
@RequestMapping(value = "/rabbitmqMsg/exchangeMsg")
@RestController
public class ExchangeMsgController extends WebBaseController {

    private final static String MODULE_NAME = "消息队列--交换机管理";
    private final static String KEY_PREFIX = "/rabbitmqMsg/exchangeMsg";
    @Autowired
    private ISetRabbitmqExchangeService iSetRabbitmqExchangeService;
    @Autowired
    private ISetRabbitmqQueueService iSetRabbitmqQueueService;

    /**
     * 加载交换机表格
     * @param dto
     * @return
     */
    @PostMapping(value = "loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载交换机表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"加载交换机表格",key = KEY_PREFIX+"loadTable")
    @RequestPermission(value = "rabbitmqMsg:exchangeMsg/loadTable")
    public ActionResult<IPage<SetRabbitmqExchangeView>> loadTable(SetRabbitmqExchangeDto dto){

        IPage<SetRabbitmqExchangeView> res = iSetRabbitmqExchangeService.selectPage(dto);
        return ActionResult.ok(res);
    }

    /**
     * 刷新Redis缓存，
     * @return
     */
    @PostMapping(value = "refreshCache")
    @RequestLog(module = MODULE_NAME,content = "刷新Redis缓存交换机",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"刷新Redis缓存交换机",key = KEY_PREFIX+"refreshCache")
    @RequestPermission(value = "rabbitmqMsg:exchangeMsg/refreshCache")
    public ActionResult<Boolean> refreshCache(){

        List<SetRabbitmqExchangeModel> list = iSetRabbitmqExchangeService.getBaseMapper().selectList(null);
        redisService.set(RedisConstant.KEY.RABBITMQ_EXCHANGE, list);
        return ActionResult.ok(true);
    }

    /**
     * 删除交换机
     * @param dto
     * @return
     */
    @PostMapping(value = "deleteById")
    @RequestLog(module = MODULE_NAME,content = "根据ID删除交换机",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"根据ID删除交换机",key = KEY_PREFIX+"deleteById")
    @RequestPermission(value = "rabbitmqMsg:exchangeMsg/deleteById")
    public ActionResult<Boolean> deleteById(SetRabbitmqExchangeDto dto){

        boolean remove = iSetRabbitmqExchangeService.removeById(dto.getId());
        if(!remove){
            return ActionResult.error("删除失败");
        }
        /** 刷新缓存 */
        this.refreshCache();
        return ActionResult.ok(remove);
    }

    /**
     * 新增交换机
     * @param dto
     * @return
     */
    @PostMapping(value = "insert")
    @RequestLog(module = MODULE_NAME,content = "新增交换机",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"新增交换机",key = KEY_PREFIX+"insert")
    @RequestPermission(value = "rabbitmqMsg:exchangeMsg/insert")
    public ActionResult<Boolean> insert(@RequestBody SetRabbitmqExchangeDto dto, HttpServletRequest request){

        SetRabbitmqExchangeModel model = dto.getModel();
        model.setCreateTime(System.currentTimeMillis()/1000L);
        model.setCreateUserId(this.getUser(request).getId());

        boolean save = iSetRabbitmqExchangeService.save(model);
        if(!save){
            return ActionResult.error("新增交换机失败");
        }
        /** 刷新缓存 */
        this.refreshCache();
        return ActionResult.ok(save);
    }

    /**
     * 修改交换机
     * @param dto
     * @return
     */
    @PostMapping(value = "update")
    @RequestLog(module = MODULE_NAME,content = "修改交换机",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"修改交换机",key = KEY_PREFIX+"update")
    @RequestPermission(value = "rabbitmqMsg:exchangeMsg/update")
    public ActionResult<Boolean> update(@RequestBody SetRabbitmqExchangeDto dto, HttpServletRequest request){

        SetRabbitmqExchangeModel model = dto.getModel();
        model.setUpdateTime(System.currentTimeMillis()/1000L);

        boolean modify = iSetRabbitmqExchangeService.modify(model);
        if(!modify){
            return ActionResult.error("修改交换机失败");
        }
        /** 刷新缓存 */
        this.refreshCache();
        return ActionResult.ok(modify);
    }

    /**
     * 根据交换机ID获取绑定队列
     * @param dto
     * @return
     */
    @PostMapping(value = "getQueueById")
    @RequestLog(module = MODULE_NAME,content = "根据交换机ID获取绑定队列",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"根据交换机ID获取绑定队列",key = KEY_PREFIX+"getQueueById")
    @RequestPermission(value = "rabbitmqMsg:exchangeMsg/getQueueById")
    public ActionResult<List<SetRabbitmqQueueModel>> getQueueById(SetRabbitmqExchangeDto dto){

        List<SetRabbitmqQueueModel> list = iSetRabbitmqQueueService.getBaseMapper().selectList(new QueryWrapper<SetRabbitmqQueueModel>() {{
            eq("exchange_id", dto.getId());
        }});
        return ActionResult.ok(list);
    }
}
