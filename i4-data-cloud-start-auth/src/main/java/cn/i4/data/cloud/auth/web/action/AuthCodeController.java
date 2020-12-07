package cn.i4.data.cloud.auth.web.action;

import cn.i4.data.cloud.auth.web.WebBaseController;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.base.util.RandomUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.core.entity.dto.UserDto;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wf.captcha.ArithmeticCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangjc
 * @title: AuthCodeController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1717:32
 */
@Controller
@RequestMapping(value = "/auth/code")
public class AuthCodeController extends WebBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AuthCodeController.class);

    @Autowired
    private IUserService iUserService;

    /**
     * 获取验证码
     * @return
     */
    @RequestMapping(value = "/getAuthCode",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<Map<String,Object>> getAuthCode(){
        try {
            ArithmeticCaptcha captcha = new ArithmeticCaptcha(135,36);//算术类型，设置宽高
            captcha.setLen(3);//两位数运算
            String result = captcha.text();//运算结果，比对值
            String redisKey = UUID.randomUUID().toString();

            //redis保存60秒
            redisService.hset(RedisConstant.HASH_KEY.AUTH_CODE,redisKey,result,RedisConstant.TIMEOUT.AUTH_CODE);

            Map<String,Object> map = new HashMap<String, Object>(){{
                put("image",captcha.toBase64());
                put("key",redisKey);
            }};
            return ActionResult.ok(map);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 获取验证接口的token（随机）,redis存储
     * @return
     */
    @RequestMapping(value = "/getAuthToken",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<String> getAuthToken(){
        try {
            String uuid = UUID.randomUUID().toString();
            redisService.hset(RedisConstant.HASH_KEY.AUTH_TOKEN,uuid,"OK");
            return ActionResult.ok(uuid);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 获取手机验证码，（1、token验证拦截，2、手机号是否为用户库中可用）
     * @param token
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getPhoneCode",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<String> getPhoneCode(String token,String phone){
        try {
            /** token验证 */
            String isOK = (String) redisService.hget(RedisConstant.HASH_KEY.AUTH_TOKEN, token);
            if(StringUtil.isNullOrEmpty(isOK)){
                return ActionResult.error("验证token为空，无法发送验证码");
            }

            // 删除已用过的token
            redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.AUTH_TOKEN, token);

            /** 手机号码验证 */
            UserModel model = iUserService.getOne(new QueryWrapper<UserModel>() {{
                eq("phone", phone);
            }});
            if(model == null){
                return ActionResult.error("当前手机号码不在用户库中");
            }

            Integer code = RandomUtil.getFourCode();
            String uuid = UUID.randomUUID().toString();
            redisService.hset(RedisConstant.HASH_KEY.SMS_CODE,uuid,code,RedisConstant.TIMEOUT.SMS_CODE);
            // 此处应该调用短信验证码的接口来发送，为了测试，直接日志打印调试吧
            logger.info("当前短信验证码如下：[{}]",code);

            return ActionResult.ok(uuid);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 注册时获取手机验证码，（1、token验证拦截，2、手机号是否已被使用）
     * @param token
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getPhoneCodeByRegister",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<String> getPhoneCodeByRegister(String token,String phone){
        try {
            /** token验证 */
            String isOK = (String) redisService.hget(RedisConstant.HASH_KEY.AUTH_TOKEN, token);
            if(StringUtil.isNullOrEmpty(isOK)){
                return ActionResult.error("验证token为空，无法发送验证码");
            }

            // 删除已用过的token
            redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.AUTH_TOKEN, token);

            /** 手机号码验证 */
            UserModel model = iUserService.getOne(new QueryWrapper<UserModel>() {{
                eq("phone", phone);
            }});
            if(model != null){
                return ActionResult.error("该手机号码已被注册");
            }

            Integer code = RandomUtil.getFourCode();
            String uuid = UUID.randomUUID().toString();
            redisService.hset(RedisConstant.HASH_KEY.SMS_CODE,uuid,code,RedisConstant.TIMEOUT.SMS_CODE);
            // 此处应该调用短信验证码的接口来发送，为了测试，直接日志打印调试吧
            logger.info("当前短信验证码如下：[{}]",code);

            return ActionResult.ok(uuid);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 获取邮箱验证码
     * @param email
     * @param token
     * @return
     */
    @RequestMapping(value = "/getEmailCode",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<String> getEmailCode(String email,String token){
        try {
            /** token验证 */
            String isOK = (String) redisService.hget(RedisConstant.HASH_KEY.AUTH_TOKEN, token);
            if(StringUtil.isNullOrEmpty(isOK)){
                return ActionResult.error("验证token为空，无法发送验证码");
            }

            // 删除已用过的token
            redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.AUTH_TOKEN, token);

            /** 邮箱验证 */
            UserModel model = iUserService.getOne(new QueryWrapper<UserModel>() {{
                eq("email", email);
            }});
            if(model == null){
                return ActionResult.error("当前电子邮箱不在用户库中");
            }

            Integer code = RandomUtil.getFourCode();
            String uuid = UUID.randomUUID().toString();
            redisService.hset(RedisConstant.HASH_KEY.EMAIL_CODE,uuid,code,RedisConstant.TIMEOUT.EMAIL_CODE);
            // 此处应该调用邮箱验证码的接口来发送，为了测试，直接日志打印调试吧
            logger.info("当前邮箱验证码如下：[{}]",code);

            return ActionResult.ok(uuid);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 获取注册邮箱验证码
     * @param email
     * @param token
     * @return
     */
    @RequestMapping(value = "/getEmailCodeByRegister",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<String> getEmailCodeByRegister(String email,String token){
        try {
            /** token验证 */
            String isOK = (String) redisService.hget(RedisConstant.HASH_KEY.AUTH_TOKEN, token);
            if(StringUtil.isNullOrEmpty(isOK)){
                return ActionResult.error("验证token为空，无法发送验证码");
            }

            // 删除已用过的token
            redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.AUTH_TOKEN, token);

            /** 邮箱验证 */
            UserModel model = iUserService.getOne(new QueryWrapper<UserModel>() {{
                eq("email", email);
            }});
            if(model != null){
                return ActionResult.error("该电子邮箱已被注册");
            }

            Integer code = RandomUtil.getFourCode();
            String uuid = UUID.randomUUID().toString();
            redisService.hset(RedisConstant.HASH_KEY.EMAIL_CODE,uuid,code,RedisConstant.TIMEOUT.EMAIL_CODE);
            // 此处应该调用邮箱验证码的接口来发送，为了测试，直接日志打印调试吧
            logger.info("当前邮箱验证码如下：[{}]",code);

            return ActionResult.ok(uuid);
        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return ActionResult.error();
        }
    }

    /**
     * 检测手机验证码
     * @param dto
     * @return
     */
    @RequestMapping(value = "/checkPhoneCode",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<UserModel> checkPhoneCode(UserDto dto){
        /** 验证码检验 */
        Integer redisCode = (Integer) redisService.hget(RedisConstant.HASH_KEY.SMS_CODE,dto.getCodeKey());
        if(!dto.getCode().equals(redisCode)){
            return ActionResult.error("手机验证码错误，请重试");
        }

        // 删除验证码
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.SMS_CODE,dto.getCodeKey());

        /** 手机检验 */
        UserModel userModel = iUserService.getOne(new QueryWrapper<UserModel>() {{
            eq("phone", dto.getPhone());
            eq("loginname",dto.getLoginName());
        }});
        if(userModel == null){
            return ActionResult.error("账号与预留手机号码不一致");
        }
        return ActionResult.ok(userModel);
    }

    /**
     * 检测邮箱验证码
     * @param dto
     * @return
     */
    @RequestMapping(value = "/checkEmailCode",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ActionResult<UserModel> checkEmailCode(UserDto dto){
        /** 验证码检验 */
        Integer redisCode = (Integer) redisService.hget(RedisConstant.HASH_KEY.EMAIL_CODE,dto.getCodeKey());
        if(!dto.getCode().equals(redisCode)){
            return ActionResult.error("邮箱验证码错误，请重试");
        }

        // 删除验证码
        redisService.hashDeleteHashKey(RedisConstant.HASH_KEY.EMAIL_CODE,dto.getCodeKey());

        /** 邮箱检验 */
        UserModel userModel = iUserService.getOne(new QueryWrapper<UserModel>() {{
            eq("email", dto.getEmail());
            eq("loginname",dto.getLoginName());
        }});
        if(userModel == null){
            return ActionResult.error("账号与预留邮箱不一致");
        }
        return ActionResult.ok(userModel);
    }

}
