package cn.i4.data.cloud.system.web.action.systemMsg;

import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.MD5Util;
import cn.i4.data.cloud.core.entity.dto.UserDto;
import cn.i4.data.cloud.core.entity.model.RoleModel;
import cn.i4.data.cloud.core.entity.model.UserInfoModel;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.entity.model.UserRoleModel;
import cn.i4.data.cloud.core.entity.view.RoleView;
import cn.i4.data.cloud.core.entity.view.UserView;
import cn.i4.data.cloud.core.service.IRoleService;
import cn.i4.data.cloud.core.service.IUserInfoService;
import cn.i4.data.cloud.core.service.IUserRoleService;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangjc
 * @title: UserMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1414:29
 */
@RequestMapping(value = "/systemMsg/userMsg")
@RestController
public class UserMsgController extends WebBaseController {

    private static final String MODULE_NAME = "系统管理--用户管理";

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserInfoService iUserInfoService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserRoleService iUserRoleService;

    /**
     * 加载用户表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载用户表格",type = RequestType.SELECT)
    public ActionResult<IPage<UserView>> loadTable(UserDto dto){
        IPage<UserView> res = iUserService.selectPage(dto);
        return ActionResult.ok(res);
    }

    /**
     * 改变用户状态
     * @param dto
     * @return
     */
    @PostMapping(value = "/changeStatus")
    @RequestLog(module = MODULE_NAME,content = "改变用户状态",type = RequestType.UPDATE)
    public ActionResult<Boolean> changeStatus(UserDto dto){

        UserModel user = iUserService.getById(dto.getUserId());
        user.setStatus(dto.getStatus());
        user.setUpdateTime(System.currentTimeMillis()/1000L);
        boolean modify = iUserService.modify(user);
        if(modify){
            return ActionResult.ok(modify);
        }else{
            return ActionResult.error("改变用户状态失败");
        }
    }

    /**
     * 根据userId删除，删除userInfo，删除userRole，删除redis缓存
     * @param dto
     * @return
     */
    @PostMapping(value = "/deleteById")
    @RequestLog(module = MODULE_NAME,content = "根据userId删除用户",type = RequestType.DELETE)
    public ActionResult<Boolean> deleteById(UserDto dto){

        /** 删除用户 */
        boolean remove = iUserService.removeById(dto.getUserId());
        if(!remove){
            return ActionResult.error("删除用户失败");
        }

        /** 删除redis */
        redisService.del(RedisConstant.KEY.LOGIN_USER_PREFIX+dto.getUserId());
        redisService.del(RedisConstant.KEY.LOGIN_USER_INFO_PREFIX+dto.getUserId());
        redisService.del(RedisConstant.KEY.LOGIN_USER_ROLE_MENU_PREFIX+dto.getUserId());

        /** 删除用户info */
        remove = iUserInfoService.remove(new QueryWrapper<UserInfoModel>(){{
            eq("user_id",dto.getUserId());
        }});
        if(!remove){
            return ActionResult.error("删除用户信息失败");
        }

        /** 删除用户角色（不一定有） */
        iUserRoleService.remove(new QueryWrapper<UserRoleModel>() {{
            eq("user_id", dto.getUserId());
        }});
        return ActionResult.ok(remove);
    }

    /**
     * 添加的登录名验重
     * @param dto
     * @return
     */
    @PostMapping(value = "/checkUnique")
    @RequestLog(module = MODULE_NAME,content = "添加的登录名验重",type = RequestType.INSERT)
    public ActionResult<Boolean> checkUnique(UserDto dto){

        UserModel userModel = iUserService.getOne(new QueryWrapper<UserModel>() {{
            eq("loginname", dto.getLoginName());
        }});
        if(userModel == null){
            return ActionResult.ok(true);
        }else{
            return ActionResult.error("该登录名已存在");
        }
    }

    /**
     * 添加保存用户
     * @param dto
     * @param request
     * @return
     */
    @PostMapping(value = "/addSave")
    @RequestLog(module = MODULE_NAME,content = "添加保存用户",type = RequestType.INSERT)
    public ActionResult<Boolean> addSave(UserDto dto,HttpServletRequest request){

        UserModel model = new UserModel();
        model.setStatus(dto.getStatus());
        model.setLoginname(dto.getLoginName());
        model.setPassword(MD5Util.getMD5String(dto.getPassword()));
        model.setCreateTime(System.currentTimeMillis()/1000L);
        boolean save = iUserService.save(model);
        if(!save){
            return ActionResult.error("新增用户失败");
        }

        /** 新增userInfo */
        UserInfoModel infoModel = new UserInfoModel();
        infoModel.setUserId(model.getId());
        infoModel.setHeadimage(this.getSystemConstant().get("userHeadImage").toString());
        infoModel.setCreateTime(System.currentTimeMillis()/1000L);
        save = iUserInfoService.save(infoModel) && save;
        if(save){
            return ActionResult.ok(save);
        }
        return ActionResult.error("新增用户失败");
    }

    /**
     * 获取用户角色（携带对比的所有权限）
     * @param dto
     * @return
     */
    @PostMapping(value = "/getUserRole")
    @RequestLog(module = MODULE_NAME,content = "获取用户角色（携带对比的所有权限）",type = RequestType.SELECT)
    public ActionResult<Map<String,Object>> getUserRole(UserDto dto){

        List<RoleView> userRole = iRoleService.selectRolesByUserId(dto.getUserId());
        List<RoleModel> roleList = iRoleService.getBaseMapper().selectList(null);
        Map<String,Object> res = new HashMap<String, Object>(){{
            put("userRole",userRole);
            put("roleList",roleList);
        }};
        return ActionResult.ok(res);
    }

    /**
     * 设置用户角色
     * @param dto
     * @return
     */
    @PostMapping(value = "/setUserRole")
    @RequestLog(module = MODULE_NAME,content = "设置用户角色",type = RequestType.UPDATE)
    public ActionResult<Boolean> setUserRole(@RequestBody UserDto dto){

        boolean remove = iUserRoleService.remove(new QueryWrapper<UserRoleModel>() {{
            eq("user_id", dto.getUserId());
        }});
        if(!remove){
            return ActionResult.error("删除用户原有角色失败");
        }
        boolean save = true;
        for(Integer roleId:dto.getRoleList()){
            UserRoleModel model = new UserRoleModel();
            model.setUserId(dto.getUserId());
            model.setRoleId(roleId);
            save = iUserRoleService.save(model) && save;
        }
        if(save){
            return ActionResult.ok(save);
        }
        return ActionResult.error("批量添加用户角色失败，请检查数据库连接是否正常");
    }

}
