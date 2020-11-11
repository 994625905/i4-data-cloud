package cn.i4.data.cloud.system.config;

import cn.hutool.core.convert.Convert;
import cn.i4.data.cloud.base.constant.RedisConstant;
import cn.i4.data.cloud.base.util.CookieUtil;
import cn.i4.data.cloud.base.util.JWTUtil;
import cn.i4.data.cloud.base.util.StringUtil;
import cn.i4.data.cloud.cache.service.RedisService;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义权限控制标签
 * @author wangjc
 * @title: PermissionTagDirective
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/9-14:32
 */
@Component
public class PermissionTagDirective implements TemplateDirectiveModel {

    @Autowired
    protected RedisService redisService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

        // 获取标签参数值  <@permission value="user:getId"> </@permission>
        String perm = Convert.toStr(map.get("value"));

        if(!StringUtil.isNullOrEmpty(perm)){

            /** 获取当前用户的所有权限 */
            List<MenuButtonView> menuList = new ArrayList<>();
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if(servletRequestAttributes != null){
                HttpServletRequest request = servletRequestAttributes.getRequest();
                String authorization = CookieUtil.getCookieValue(request, "authorization");

                menuList = redisService.get(RedisConstant.KEY.LOGIN_USER_ROLE_MENU_TREE_PREFIX + JWTUtil.getUserId(authorization), List.class);
            }

            /** 权限验证 */
            if(checkPermission(menuList,perm)){
                templateDirectiveBody.render(environment.getOut());//显示
            }

        }

    }

    /**
     * 验证权限
     * @param list
     * @param permission
     * @return
     */
    private Boolean checkPermission(List<MenuButtonView> list, String permission){
        Boolean flag = false;
        if(list != null && list.size() >= 1){
            for(MenuButtonView menuButton:list){
                if(permission.equals(menuButton.getPermission())){
                    flag = true;
                    break;
                }else{
                    if(checkPermission(menuButton.getChild(),permission)){
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
