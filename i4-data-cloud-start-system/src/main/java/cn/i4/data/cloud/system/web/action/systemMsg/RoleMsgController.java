package cn.i4.data.cloud.system.web.action.systemMsg;

import cn.i4.data.cloud.base.annotation.RequestLog;
import cn.i4.data.cloud.base.annotation.RequestType;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.dto.RoleDto;
import cn.i4.data.cloud.core.entity.model.RoleMenuModel;
import cn.i4.data.cloud.core.entity.model.RoleModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;
import cn.i4.data.cloud.core.entity.view.RoleView;
import cn.i4.data.cloud.core.service.IRoleMenuService;
import cn.i4.data.cloud.core.service.IRoleService;
import cn.i4.data.cloud.system.web.WebBaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统管理--角色管理
 * @author wangjc
 * @title: RoleMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/1515:09
 */
@RequestMapping(value = "/systemMsg/roleMsg")
@RestController
public class RoleMsgController extends WebBaseController {

    private static final String MODULE_NAME = "系统管理--角色管理";

    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IRoleMenuService iRoleMenuService;

    /**
     * 加载表格
     * @param dto
     * @return
     */
    @PostMapping(value = "/loadTable")
    @RequestLog(module = MODULE_NAME,content = "加载表格",type = RequestType.SELECT)
    public ActionResult<IPage<RoleView>> loadTable(RoleDto dto){

        IPage<RoleView> res = iRoleService.selectPage(dto);
        return ActionResult.ok(res);
    }

    /**
     * 删除角色，级联删除角色--菜单权限
     * @param dto
     * @return
     */
    @PostMapping(value = "/deleteById")
    @RequestLog(module = MODULE_NAME,content = "删除角色，级联删除角色--菜单权限",type = RequestType.DELETE)
    public ActionResult<Boolean> deleteById(RoleDto dto,HttpServletRequest request){

        boolean remove = iRoleService.removeById(dto.getId());
        if(!remove){
            return ActionResult.error("删除角色失败");
        }

        /** 级联删除角色--菜单 */
        iRoleMenuService.getBaseMapper().delete(new QueryWrapper<RoleMenuModel>() {{
            eq("role_id", dto.getId());
        }});
        /** 刷新redis */
        this.refreshUserRoleMenuButton(request);
        return ActionResult.ok(remove);
    }

    /**
     * 获取角色下的菜单
     * @param dto
     * @return
     */
    @PostMapping(value = "/getMenuButtonByRoleId")
    @RequestLog(module = MODULE_NAME,content = "获取角色下的菜单",type = RequestType.SELECT)
    public ActionResult<List<MenuButtonView>> getMenuButtonByRoleId(RoleDto dto){

        List<MenuButtonView> roleMenuList = iMenuButtonService.getMenuButtonByRoleId(dto.getId());
        return ActionResult.ok(roleMenuList);
    }

    /**
     * 检验name的唯一性
     * @param dto
     * @return
     */
    @PostMapping(value = "/checkUnique")
    @RequestLog(module = MODULE_NAME,content = "检验name的唯一性",type = RequestType.SELECT)
    public ActionResult<Boolean> checkUnique(RoleDto dto){

        RoleModel model = iRoleService.getOne(new QueryWrapper<RoleModel>() {{
            eq("name", dto.getName());
            /** 修改的话，去掉当前id */
            if(dto.getId() != null){
                ne("id",dto.getId());
            }
        }});
        if(model == null){
            return ActionResult.ok(true);
        }
        return ActionResult.error("该角色名称已存在");
    }

    /**
     * 保存
     * @param dto
     * @return
     */
    @PostMapping(value = "/save")
    @RequestLog(module = MODULE_NAME,content = "保存角色",type = RequestType.INSERT)
    public ActionResult<Boolean> save(@RequestBody RoleDto dto,HttpServletRequest request){

        Boolean res = null;
        RoleModel model = dto.getModel();

        /** 区别新增与修改 */
        if(model.getId() == null){
            model.setCreateTime(System.currentTimeMillis()/1000L);
            res = iRoleService.save(model);
        }else{
            model.setUpdateTime(System.currentTimeMillis()/1000L);
            res = iRoleService.modify(model);
            //修改删除之前的权限
            iRoleMenuService.remove(new QueryWrapper<RoleMenuModel>(){{
                eq("role_id",model.getId());
            }});
        }
        if(!res){
            return ActionResult.error("保存角色失败");
        }

        /** 更新权限 */
        for(Integer menuId:dto.getMenuIdList()){
            RoleMenuModel menuModel = new RoleMenuModel();
            menuModel.setMenuButtonId(menuId);
            menuModel.setRoleId(model.getId());
            res = iRoleMenuService.save(menuModel) && res;
        }
        if(res){
            /** 刷新redis */
            this.refreshUserRoleMenuButton(request);
            return ActionResult.ok(res);
        }
        return ActionResult.error("保存角色权限失败");
    }

}

