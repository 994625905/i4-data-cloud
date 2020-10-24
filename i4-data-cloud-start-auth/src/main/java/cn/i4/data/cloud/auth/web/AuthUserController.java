package cn.i4.data.cloud.auth.web;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.MD5Util;
import cn.i4.data.cloud.core.entity.dto.UserDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeModel;
import cn.i4.data.cloud.core.entity.model.UserInfoModel;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.service.IInviteCodeService;
import cn.i4.data.cloud.core.service.IUserInfoService;
import cn.i4.data.cloud.core.service.IUserRoleService;
import cn.i4.data.cloud.core.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjc
 * @title: UserController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1716:08
 */
@Controller
@RequestMapping(value = "/auth/user")
public class AuthUserController extends WebBaseController{

    private static final Logger logger = LoggerFactory.getLogger(AuthUserController.class);

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserInfoService iUserInfoService;
    @Autowired
    private IUserRoleService iUserRoleService;
    @Autowired
    private IInviteCodeService iInviteCodeService;

    /**
     * 修改用户，用户信息
     * @return
     */
    @RequestMapping(value = "/updateUser",method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult<Map<String,Object>> updateUser(@RequestBody UserDto dto){
        try {
            UserModel user = dto.getUser();
            UserInfoModel userInfo = dto.getUserInfo();
            iUserService.modify(user);
            iUserInfoService.modify(userInfo);
            Map<String,Object> map = new HashMap<String, Object>(){{
                put("user",user);
                put("userInfo",userInfo);
            }};
            return ActionResult.ok(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 修改密码
     * @param dto
     * @return
     */
    @RequestMapping(value = "/changePassword",method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult<Boolean> changePassword(UserDto dto){
        try {
            UserModel userModel = iUserService.getById(dto.getUserId());
            userModel.setPassword(MD5Util.getMD5String(dto.getPassword()));
            userModel.setUpdateTime(System.currentTimeMillis()/1000L);
            boolean modify = iUserService.modify(userModel);
            return ActionResult.ok(modify);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 注册账号，检验loginName是否冲突
     * @param dto
     * @return
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult<UserInfoModel> register(UserDto dto){
        try {
            /** 内部邀请码的检测 */
            InviteCodeModel invite = iInviteCodeService.getOne(new QueryWrapper<InviteCodeModel>() {{
                eq("code", dto.getInviteCode());
                gt("over_time", System.currentTimeMillis() / 1000L);
            }});
            if(invite == null){
                return ActionResult.error("该内部邀请码无效");
            }

            /** 检验loginName */
            UserModel one = iUserService.getOne(new QueryWrapper<UserModel>() {{
                eq("loginname", dto.getLoginName());
            }});
            if(one != null){
                return ActionResult.error("该登陆名称已被使用");
            }

            /** 新增注册用户 */
            UserModel user = new UserModel();
            user.setCreateTime(System.currentTimeMillis()/1000L);
            user.setLoginname(dto.getLoginName());
            user.setPassword(MD5Util.getMD5String(dto.getPassword()));
            user.setRealname(dto.getRealName());
            user.setStatus(invite.getUserStatus());
            boolean save = iUserService.save(user);

            UserInfoModel userInfo = new UserInfoModel();
            userInfo.setCreateTime(System.currentTimeMillis()/1000L);
            userInfo.setUserId(user.getId());
            userInfo.setHeadimage(this.getSystemConstant().get("userHeadImage").toString());
            save = iUserInfoService.save(userInfo) && save;
            if(!save){
                return ActionResult.error("新增注册用户失败");
            }

            /** 根据邀请码设置角色 */
            boolean setRole = iUserRoleService.setUserRoleByInviteCodeId(user.getId(),invite.getId());
            if(!setRole){
                return ActionResult.error("根据邀请码设置角色失败");
            }
            return ActionResult.ok(userInfo);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 根据userId修改phone，检验手机号是否存在
     * @return
     */
    @RequestMapping(value = "/updatePhoneByUserId",method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult<UserModel> updatePhoneByUserId(UserDto dto){
        try {
            /** 验证码检验 */
            Integer redisCode = (Integer) redisService.hget(RedisConstant.HASH_KEY.SMS_CODE,dto.getCodeKey());
            if(!dto.getCode().equals(redisCode)){
                return ActionResult.error("手机验证码错误，请重试");
            }

            // 删除验证码
            redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.SMS_CODE,dto.getCodeKey());

            /** 手机号码验重 */
            UserModel one = iUserService.getOne(new QueryWrapper<UserModel>() {{
                eq("phone", dto.getPhone());
            }});
            if(one != null){
                return ActionResult.error("该手机号码已被注册");
            }

            /** 保存 */
            UserModel user = iUserService.getById(dto.getUserId());
            user.setPhone(dto.getPhone());
            user.setUpdateTime(System.currentTimeMillis()/1000L);
            boolean modify = iUserService.modify(user);
            if(!modify){
                return ActionResult.error("设置手机号码失败");
            }
            return ActionResult.ok(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 根据userId修改email，检验邮箱是否存在
     * @return
     */
    @RequestMapping(value = "/updateEmailByUserId",method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult<UserModel> updateEmailByUserId(UserDto dto){
        try {
            /** 验证码检验 */
            Integer redisCode = (Integer) redisService.hget(RedisConstant.HASH_KEY.EMAIL_CODE,dto.getCodeKey());
            if(!dto.getCode().equals(redisCode)){
                return ActionResult.error("邮箱验证码错误，请重试");
            }

            // 删除验证码
            redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.EMAIL_CODE,dto.getCodeKey());

            /** 电子邮箱验重 */
            UserModel one = iUserService.getOne(new QueryWrapper<UserModel>() {{
                eq("email", dto.getEmail());
            }});
            if(one != null){
                return ActionResult.error("该电子邮箱已被注册");
            }

            /** 保存 */
            UserModel user = iUserService.getById(dto.getUserId());
            user.setEmail(dto.getEmail());
            user.setUpdateTime(System.currentTimeMillis()/1000L);
            boolean modify = iUserService.modify(user);
            if(!modify){
                return ActionResult.error("设置电子邮箱失败");
            }
            return ActionResult.ok(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 修改用户信息
     * @param dto
     * @return
     */
    @RequestMapping(value = "/updateUserInfo",method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult<Boolean> updateUserInfo(@RequestBody UserDto dto){
        try {
            UserInfoModel userInfo = dto.getUserInfo();
            userInfo.setUpdateTime(System.currentTimeMillis()/1000L);
            boolean modify = iUserInfoService.modify(userInfo);
            if(modify){
                return ActionResult.ok(modify);
            }
            return ActionResult.error("修改用户信息失败");
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

}
