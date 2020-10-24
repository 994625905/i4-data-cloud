package cn.i4.data.cloud.auth.web;

import cn.i4.data.cloud.core.entity.dto.UserDto;
import cn.i4.data.cloud.core.entity.model.UserInfoModel;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.auth.help.AuthHelp;
import cn.i4.data.cloud.core.service.IUserInfoService;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.constant.UserConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.result.ResultEnum;
import cn.i4.data.cloud.base.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjc
 * @title: LoginController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/109:42
 */
@Controller
@RequestMapping(value = "/auth/do")
public class AuthLoginController extends WebBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AuthLoginController.class);

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserInfoService iUserInfoService;

    /**
     * 账号密码登录动作(以此类推，可添加手机验证码，微信……第三方登录接口)
     * 离职不可访问，初始化需先修改密码，成功返回重定向地址，页面层跳转
     * @param dto
     * @param response
     * @return
     */
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult<Map<String,Object>> login(UserDto dto, HttpServletResponse response){
        try {
            /** 账号密码登录 */
            UserModel userModel = iUserService.login(dto.getLoginName(),MD5Util.getMD5String(dto.getPassword()));
            if(userModel == null){
                return ActionResult.error(ResultEnum.LOGIN_FAIL_PWD.getCode(),ResultEnum.LOGIN_FAIL_PWD.getMsgCn());
            }
            /** 用户身份判断 */
            if(userModel.getStatus().equals(UserConstant.STATUS.LEAVE) || userModel.getStatus().equals(UserConstant.STATUS.OTHER)){
                return ActionResult.error(ResultEnum.LOGIN_FAIL_USER.getCode(),ResultEnum.LOGIN_FAIL_USER.getMsgCn());
            }

            UserInfoModel userInfo = iUserInfoService.getOne(new QueryWrapper<UserInfoModel>() {{
                eq("user_id", userModel.getId());
            }});

            /** 初始化用户需要先完善个人信息，然后更改密码 */
            if(userModel.getStatus().equals(UserConstant.STATUS.INIT)){
                Map<String,Object> map = new HashMap<String, Object>(){{
                    put("user",userModel);
                    put("userInfo",userInfo);
                }};
                return ActionResult.ok(map);
            }

            /** 正常登录 */
            String authorization = AuthHelp.login(userModel, userInfo, redisService, response);

            logger.debug("登录成功，认证token[{}]",authorization);

            Map<String,Object> map = new HashMap<String, Object>(){{
                put("user",userModel);
                put("userInfo",userInfo);
                put("authorization",authorization);
            }};
            return ActionResult.ok(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 短信验证码登录，
     * 验证成功后，根据手机号码来获取用户信息
     * @param dto
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginBySms",method = {RequestMethod.POST})
    @ResponseBody
    public ActionResult<Map<String,Object>> loginBySms(UserDto dto, HttpServletResponse response){
        try {
            /** 验证码验证 */
            String Integer = (String) redisService.hget(RedisConstant.HASH_KEY.SMS_CODE,dto.getCodeKey());
            if(!dto.getCode().equals(Integer)){
                return ActionResult.error(ResultEnum.LOGIN_FAIL_CODE.getCode(),ResultEnum.LOGIN_FAIL_CODE.getMsgCn());
            }

            /** 手机号码登录 */
            UserModel userModel = iUserService.getOne(new QueryWrapper<UserModel>() {{
                eq("phone", dto.getPhone());
            }});
            if(userModel == null){
                return ActionResult.error(ResultEnum.LOGIN_FAIL_PHONE.getCode(),ResultEnum.LOGIN_FAIL_PHONE.getMsgCn());
            }

            /** 用户身份判断 */
            if(userModel.getStatus().equals(UserConstant.STATUS.LEAVE) || userModel.getStatus().equals(UserConstant.STATUS.OTHER)){
                return ActionResult.error(ResultEnum.LOGIN_FAIL_USER.getCode(),ResultEnum.LOGIN_FAIL_USER.getMsgCn());
            }

            /** 正常登录 */
            UserInfoModel userInfo = iUserInfoService.getOne(new QueryWrapper<UserInfoModel>() {{
                eq("user_id", userModel.getId());
            }});
            String authorization = AuthHelp.login(userModel, userInfo, redisService, response);

            logger.debug("登录成功，认证token[{}]",authorization);

            Map<String,Object> map = new HashMap<String, Object>(){{
                put("user",userModel);
                put("userInfo",userInfo);
                put("authorization",authorization);
            }};
            return ActionResult.ok(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

}
