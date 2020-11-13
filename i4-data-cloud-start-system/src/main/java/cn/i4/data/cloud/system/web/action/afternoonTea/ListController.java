package cn.i4.data.cloud.system.web.action.afternoonTea;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.CookieUtil;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaView;
import cn.i4.data.cloud.core.service.IAfternoonTeaService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 下午茶--列表管理
 * @author wangjc
 * @title: ListController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/10-15:20
 */
@RequestMapping(value = "/afternoonTea/list")
@RestController
public class ListController extends WebBaseController {

    private static final String MODULE_NAME = "下午茶--列表管理";
    private static final String KEY_PREFIX = "/afternoonTea/list";
    @Autowired
    private IAfternoonTeaService iAfternoonTeaService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"--加载表格",key = KEY_PREFIX+"/loadTable")
    @RequestPermission(value = "afternoonTea:list/loadTable")
    public ActionResult<IPage<AfternoonTeaView>> loadTable(AfternoonTeaDto dto){
        IPage<AfternoonTeaView> page = iAfternoonTeaService.selectPage(dto);
        return ActionResult.ok(page);
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
    @RequestPermission(value = "afternoonTea:list/insert")
    public ActionResult<Boolean> insert(@RequestBody AfternoonTeaDto dto, HttpServletRequest request){
        AfternoonTeaModel model = dto.getModel();
        model.setCreateUserId(this.getUser(request).getId());
        model.setCreateTime(System.currentTimeMillis()/1000L);

        boolean save = iAfternoonTeaService.save(model);
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
    @RequestPermission(value = "afternoonTea:list/update")
    public ActionResult<Boolean> update(@RequestBody AfternoonTeaDto dto, HttpServletRequest request){
        AfternoonTeaModel model = dto.getModel();
        model.setUpdateTime(System.currentTimeMillis()/1000L);

        boolean modify = iAfternoonTeaService.modify(model);
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
    @RequestLog(module = MODULE_NAME,content = "删除",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--删除",key = KEY_PREFIX+"/delete")
    @RequestPermission(value = "afternoonTea:list/delete")
    public ActionResult<Boolean> delete(AfternoonTeaDto dto, HttpServletRequest request){
        boolean remove = iAfternoonTeaService.removeById(dto.getId());
        if(remove){
            return ActionResult.ok(true);
        }
        return ActionResult.error("删除失败");
    }

    /**
     * 设置下午茶选择的临时存储
     * @param dto
     * @return
     */
    @PostMapping(value = "/setTeaSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "设置下午茶选择的临时存储",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--设置下午茶选择的临时存储",key = KEY_PREFIX+"/setTeaSelectTemp")
    @RequestPermission(value = "afternoonTea:list/setTeaSelectTemp")
    public ActionResult<Boolean> setTeaSelectTemp(AfternoonTeaDto dto,HttpServletRequest request){
        String authorization = CookieUtil.getCookieValue(request, "authorization");
        boolean res = redisService.hset(RedisConstant.HASH_KEY.SELECT_AFTERNOON_TEA, authorization, dto, RedisConstant.TIMEOUT.SELECT_AFTERNOON_TEA);
        return ActionResult.ok(res);
    }

    /**
     * 获取下午茶选择的临时存储
     * @param request
     * @return
     */
    @PostMapping(value = "/getTeaSelectTemp")
    @RequestLog(module = MODULE_NAME,content = "获取下午茶选择的临时存储",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"--获取下午茶选择的临时存储",key = KEY_PREFIX+"/getTeaSelectTemp")
    @RequestPermission(value = "afternoonTea:list/getTeaSelectTemp")
    public ActionResult<AfternoonTeaDto> getTeaSelectTemp(HttpServletRequest request){
        String authorization = CookieUtil.getCookieValue(request, "authorization");
        AfternoonTeaDto param = (AfternoonTeaDto) redisService.hget(RedisConstant.HASH_KEY.SELECT_AFTERNOON_TEA, authorization);
        if(param == null){
            return ActionResult.error("请重新选择下午茶");
        }
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.SELECT_AFTERNOON_TEA, authorization);
        return ActionResult.ok(param);
    }

}
