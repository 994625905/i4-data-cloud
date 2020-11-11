package cn.i4.data.cloud.system.web.action.systemMsg;

import cn.i4.data.cloud.base.annotation.RequestLimit;
import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestPermission;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.MenuButtonDto;
import cn.i4.data.cloud.core.entity.model.MenuButtonModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjc
 * @title: MenuMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1117:58
 */
@RequestMapping(value = "/systemMsg/menuMsg")
@RestController
public class MenuMsgController extends WebBaseController {

    private static final String MODULE_NAME = "系统管理--菜单管理";
    private static final String KEY_PREFIX = "/systemMsg/menuMsg";

    /**
     * 加载菜单表格
     * @return
     */
    @PostMapping(value = "/loadMenuTable")
    @RequestLog(module = MODULE_NAME,content = "加载菜单表格",type = RequestType.SELECT)
    @RequestLimit(name = MODULE_NAME+"加载菜单表格",key = KEY_PREFIX+"loadMenuTable")
    @RequestPermission(value = "systemMsg:menuMsg/loadMenuTable")
    public ActionResult<IPage<MenuButtonView>> loadMenuTable(MenuButtonDto dto){

        IPage<MenuButtonView> page = iMenuButtonService.selectPage(dto);
        return ActionResult.ok(page);
    }

    /**
     * 删除菜单
     * @param dto
     * @return
     */
    @PostMapping(value = "/deleteById")
    @RequestLog(module = MODULE_NAME,content = "删除菜单",type = RequestType.DELETE)
    @RequestLimit(name = MODULE_NAME+"删除菜单",key = KEY_PREFIX+"deleteById")
    @RequestPermission(value = "systemMsg:menuMsg/deleteById")
    public ActionResult<Boolean> deleteById(MenuButtonDto dto,HttpServletRequest request){

        boolean remove = iMenuButtonService.removeById(dto.getId());
        if(remove){
            /** 刷新redis */
            this.refreshUserRoleMenuButton(request);
            return ActionResult.ok(remove);
        }else{
            return ActionResult.error("删除失败");
        }
    }

    /**
     * 改变状态
     * @param dto
     * @return
     */
    @PostMapping(value = "/changeStatus")
    @RequestLog(module = MODULE_NAME,content = "改变状态",type = RequestType.UPDATE)
    @RequestLimit(name = MODULE_NAME+"改变状态",key = KEY_PREFIX+"changeStatus")
    @RequestPermission(value = "systemMsg:menuMsg/changeStatus")
    public ActionResult<Boolean> changeStatus(MenuButtonDto dto,HttpServletRequest request){

        MenuButtonModel menu = iMenuButtonService.getById(dto.getId());
        menu.setStatus(dto.getStatus());
        menu.setUpdateTime(System.currentTimeMillis()/1000L);
        boolean modify = iMenuButtonService.modify(menu);
        if(modify){
            /** 刷新redis */
            this.refreshUserRoleMenuButton(request);
            return ActionResult.ok(modify);
        }else{
            return ActionResult.error("改变状态失败");
        }
    }

    /**
     * 保存
     * @param dto
     * @return
     */
    @PostMapping(value = "/save")
    @RequestLog(module = MODULE_NAME,content = "保存",type = RequestType.INSERT)
    @RequestLimit(name = MODULE_NAME+"保存",key = KEY_PREFIX+"save")
    @RequestPermission(value = "systemMsg:menuMsg/save")
    public ActionResult<Boolean> save(@RequestBody MenuButtonDto dto,HttpServletRequest request){

        Boolean save = true;
        MenuButtonModel model = dto.getModel();
        model.setUpdateTime(System.currentTimeMillis()/1000L);
        if(model.getId() == null){//新增
            model.setCreateTime(System.currentTimeMillis()/1000L);
            save = iMenuButtonService.save(model);
        }else{//保存
            save = iMenuButtonService.modify(model);
        }
        if(save){
            /** 刷新redis */
            this.refreshUserRoleMenuButton(request);
            return ActionResult.ok(save);
        }else{
            return ActionResult.error("保存失败");
        }
    }

}
