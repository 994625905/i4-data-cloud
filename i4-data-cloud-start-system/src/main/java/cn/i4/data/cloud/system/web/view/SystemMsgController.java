package cn.i4.data.cloud.system.web.view;

import cn.i4.data.cloud.core.entity.dto.MenuButtonDto;
import cn.i4.data.cloud.core.entity.dto.RoleDto;
import cn.i4.data.cloud.core.entity.dto.UserDto;
import cn.i4.data.cloud.core.entity.model.MenuButtonModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;
import cn.i4.data.cloud.core.entity.view.UserView;
import cn.i4.data.cloud.core.service.IRoleService;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.system.web.WebBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统管理的视图页面
 * @author wangjc
 * @title: SystemMsgController
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/209:46
 */
@RequestMapping(value = "/systemMsg")
@Controller
public class SystemMsgController extends WebBaseController {

    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private IUserService iUserService;

    /**
     * 加载菜单管理首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/menuMsg/index")
    public ModelAndView menuMsgIndex(HttpServletRequest request){
        ModelAndView view = this.getModelAndView("/systemMsg/menuMsg_index", request);
        return view;
    }

    /**
     * 新增菜单页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/menuMsg/add")
    public ModelAndView menuMsgAdd(MenuButtonDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/menuMsg_add", request);
        view.addObject("menu",iMenuButtonService.getById(dto.getPid()));
        view.addObject("maxSort",iMenuButtonService.getMaxSortByPid(dto.getPid())+1);
        return view;
    }

    /**
     * 新增菜单按钮页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/menuMsg/addButton")
    public ModelAndView menuMsgAddButton(MenuButtonDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/menuMsg_button_add", request);
        view.addObject("menu",iMenuButtonService.getById(dto.getPid()));
        view.addObject("maxSort",iMenuButtonService.getMaxSortByPid(dto.getPid())+1);
        return view;
    }

    /**
     * 编辑菜单页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/menuMsg/edit")
    public ModelAndView menuMsgEdit(MenuButtonDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/menuMsg_edit", request);
        List<MenuButtonView> pMenuList = iMenuButtonService.getMenuButtonsByPid(0);
        view.addObject("menu",iMenuButtonService.getById(dto.getId()));
        view.addObject("pMenuList",pMenuList);
        return view;
    }

    /**
     * 编辑菜单按钮页面
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/menuMsg/editButton")
    public ModelAndView menuMsgEditButton(MenuButtonDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/menuMsg_button_edit", request);
        MenuButtonModel buttonModel = iMenuButtonService.getById(dto.getId());
        MenuButtonModel pMenu = iMenuButtonService.getById(buttonModel.getParentId());
        view.addObject("menu",buttonModel);
        view.addObject("pMenu",pMenu);
        return view;
    }

    /**
     * 菜单按钮的详情页
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/menuMsg/detailById")
    public ModelAndView menuMsgDetailById(MenuButtonDto dto,HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/menuMsg_child", request);
        view.addObject("pid",dto.getId());
        return view;
    }

    /**
     * 加载角色管理首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/roleMsg/index")
    public ModelAndView roleMsgIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/roleMsg_index", request);
        return view;
    }

    /**
     * 新增角色页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/roleMsg/add")
    public ModelAndView roleMsgAdd(HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/roleMsg_add", request);
        List<MenuButtonView> menuButtonTree = iMenuButtonService.getMenuButtonTree();
        view.addObject("menuList",menuButtonTree);
        return view;
    }

    /**
     * 编辑角色页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/roleMsg/edit")
    public ModelAndView roleMsgEdit(RoleDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/roleMsg_edit", request);
        List<MenuButtonView> menuButtonTree = iMenuButtonService.getMenuButtonTree();
        view.addObject("menuList",menuButtonTree);
        view.addObject("role",iRoleService.getById(dto.getId()));
        return view;
    }


    /**
     * 加载用户管理模块首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/userMsg/index")
    public ModelAndView userMsgIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/userMsg_index", request);
        return view;
    }

    /**
     * 用户详情
     * @param dto
     * @param request
     * @return
     */
    @RequestMapping(value = "/userMsg/detail")
    public ModelAndView userMsgDetail(UserDto dto, HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/userMsg_detail", request);
        UserView detail = iUserService.selectById(dto.getUserId());
        view.addObject("detail",detail);
        view.addObject("roleList",iRoleService.selectRolesByUserId(dto.getUserId()));
        return view;
    }

    /**
     * 添加用户的页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/userMsg/add")
    public ModelAndView userMsgAdd(HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/userMsg_add", request);
        return view;
    }

    /**
     * 加载邀请码模块首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/inviteCode/index")
    public ModelAndView inviteCodeMsgIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/inviteCodeMsg_index", request);
        return view;
    }

    /**
     * 新增邀请码页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/inviteCode/addPage")
    public ModelAndView inviteCodeMsgAddPage(HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/inviteCodeMsg_add", request);
        return view;
    }

    /**
     * 加载系统常量模块首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/constantMsg/index")
    public ModelAndView constantMsgIndex(HttpServletRequest request){
        ModelAndView view = getModelAndView("/systemMsg/constantMsg_index", request);
        return view;
    }

}
