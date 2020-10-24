package cn.i4.data.cloud.system.web;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.controller.BaseController;
import cn.i4.data.cloud.base.util.CookieUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.entity.model.UserInfoModel;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;
import cn.i4.data.cloud.core.service.IMenuButtonService;
import cn.i4.data.cloud.core.service.ISystemConstantService;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author wangjc
 * @title: WebBaseController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1017:46
 */
public class WebBaseController extends BaseController {

    @Autowired
    protected RedisService redisService;
    @Autowired
    protected ISystemConstantService iSystemConstantService;
    @Autowired
    protected IMenuButtonService iMenuButtonService;

    /**
     * 获取模型视图，设置user和userInfo
     * @param path
     * @param request
     * @return
     */
    public ModelAndView getModelAndView(String path,HttpServletRequest request){
        ModelAndView view = super.getModelAndView(path);
        view.addObject("user",this.getUser(request));
        view.addObject("userInfo",this.getUserInfo(request));
        view.addObject("systemConstant",this.getSystemConstant());
        view.addObject("menuList",this.getUserRoleMenuButton(request));
        return view;
    }

    /**
     * 获取user，刷新时间在拦截器中
     * @param request
     * @return
     */
    protected UserModel getUser(HttpServletRequest request){
        String authorization = CookieUtil.getCookieValue(request, "authorization");
        DecodedJWT decode = JWT.decode(authorization);
        UserModel userModel = redisService.get(RedisConstant.KEY.LOGIN_USER_PREFIX + decode.getClaim("userId").asInt(), UserModel.class);
        return userModel;
    }

    /**
     * 获取user Info，刷新时间在拦截器中
     * @param request
     * @return
     */
    protected UserInfoModel getUserInfo(HttpServletRequest request){
        UserInfoModel userInfo = redisService.get(RedisConstant.KEY.LOGIN_USER_INFO_PREFIX + this.getUser(request).getId(), UserInfoModel.class);
        return userInfo;
    }

    /**
     * 获取系统常量信息
     * @return
     */
    protected Map<String,Object> getSystemConstant(){
        String json = redisService.get(RedisConstant.KEY.SYSTEM_CONSTANT, String.class);
        if(json == null){
            Map<String,Object> map = iSystemConstantService.getSystemConstant();
            redisService.set(RedisConstant.KEY.SYSTEM_CONSTANT, JSONObject.toJSONString(map),RedisConstant.TIMEOUT.SYSTEM_CONSTANT);
            return map;
        }else{
            redisService.expire(RedisConstant.KEY.SYSTEM_CONSTANT,RedisConstant.TIMEOUT.SYSTEM_CONSTANT);
            return JSONObject.parseObject(json,Map.class);
        }
    }

    /**
     * 获取登陆用户的权限菜单
     * @param request
     * @return
     */
    protected List<MenuButtonView> getUserRoleMenuButton(HttpServletRequest request){
        List<MenuButtonView> menuList = redisService.get(RedisConstant.KEY.LOGIN_USER_ROLE_MENU_PREFIX + this.getUser(request).getId(),List.class);
        if(menuList == null || menuList.size() <1){
            menuList = iMenuButtonService.getMenuButtonTreeByUserId(this.getUser(request).getId());
            redisService.set(RedisConstant.KEY.LOGIN_USER_ROLE_MENU_PREFIX + this.getUser(request).getId(),menuList,RedisConstant.TIMEOUT.LOGIN_USER_ROLE_MENU);
        }else{
            redisService.expire(RedisConstant.KEY.LOGIN_USER_ROLE_MENU_PREFIX + this.getUser(request).getId(),RedisConstant.TIMEOUT.LOGIN_USER_ROLE_MENU);
        }
        return menuList;
    }

    /**
     * 刷新登陆用户的权限菜单
     * @param request
     * @return
     */
    protected List<MenuButtonView> refreshUserRoleMenuButton(HttpServletRequest request){
        List<MenuButtonView> menuList = iMenuButtonService.getMenuButtonTreeByUserId(this.getUser(request).getId());
        redisService.set(RedisConstant.KEY.LOGIN_USER_ROLE_MENU_PREFIX + this.getUser(request).getId(),menuList,RedisConstant.TIMEOUT.LOGIN_USER_ROLE_MENU);
        return menuList;
    }

}
