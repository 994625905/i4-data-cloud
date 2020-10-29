package cn.i4.data.cloud.auth.web;

import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.controller.BaseController;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.service.ISystemConstantService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangjc
 * @title: WebBaseController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1715:56
 */
public class WebBaseController extends BaseController {

    @Autowired
    protected RedisService redisService;
    @Autowired
    protected ISystemConstantService iSystemConstantService;

    @Value("${i4.data.cloud.auth-url}")
    protected String authURL;

    /**
     * 获取模型视图
     * @param path
     * @param request
     * @return
     */
    public ModelAndView getModelAndView(String path, HttpServletRequest request){
        ModelAndView view = super.getModelAndView(path);
        view.addObject("systemConstant",this.getSystemConstant());
        return view;
    }

    /**
     * 获取系统常量信息
     * @return
     */
    protected Map<String,Object> getSystemConstant(){
        Map<String,Object> map = redisService.get(RedisConstant.KEY.SYSTEM_CONSTANT, Map.class);
        if(map == null){
            map = iSystemConstantService.getSystemConstant();
            redisService.set(RedisConstant.KEY.SYSTEM_CONSTANT, map,RedisConstant.TIMEOUT.SYSTEM_CONSTANT);
        }else{
            redisService.expire(RedisConstant.KEY.SYSTEM_CONSTANT,RedisConstant.TIMEOUT.SYSTEM_CONSTANT);
        }
        return map;
    }


}
