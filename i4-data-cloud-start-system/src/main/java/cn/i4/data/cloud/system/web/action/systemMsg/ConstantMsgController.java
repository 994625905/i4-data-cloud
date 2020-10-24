package cn.i4.data.cloud.system.web.action.systemMsg;

import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.SystemConstantDto;
import cn.i4.data.cloud.core.entity.model.SystemConstantModel;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 常量管理的控制层
 * @author wangjc
 * @title: ConstantMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1414:36
 */
@RequestMapping(value = "/systemMsg/constantMsg")
@RestController
public class ConstantMsgController extends WebBaseController {

    private static final String MODULE_NAME = "系统管理--系统常量";

    /**
     * 修改系统常量，同时更新redis缓存
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/update")
    @RequestLog(module = MODULE_NAME,content = "修改系统常量，同时更新redis缓存",type = RequestType.UPDATE)
    public ActionResult<Boolean> update(SystemConstantDto dto,HttpServletRequest request){

        SystemConstantModel model = iSystemConstantService.getById(dto.getId());
        model.setConstantValue(dto.getValue());
        boolean modify = iSystemConstantService.modify(model);
        if(modify){
            /** 更新缓存 */
            Map<String,Object> map = iSystemConstantService.getSystemConstant();
            redisService.set(RedisConstant.KEY.SYSTEM_CONSTANT, JSONObject.toJSONString(map),RedisConstant.TIMEOUT.SYSTEM_CONSTANT);
            return ActionResult.ok(modify);
        }else{
            return ActionResult.error("更新系统常量失败");
        }
    }

    /**
     * 刷新Redis缓存系统常量
     * @return
     */
    @PostMapping(value = "/refresh")
    @RequestLog(module = MODULE_NAME,content = "刷新Redis缓存系统常量",type = RequestType.UPDATE)
    public ActionResult<Boolean> refresh(){

        Map<String,Object> map = iSystemConstantService.getSystemConstant();
        redisService.set(RedisConstant.KEY.SYSTEM_CONSTANT, JSONObject.toJSONString(map),RedisConstant.TIMEOUT.SYSTEM_CONSTANT);
        return ActionResult.ok(true);
    }

}
