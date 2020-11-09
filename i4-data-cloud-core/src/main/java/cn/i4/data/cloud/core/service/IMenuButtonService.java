package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.MenuButtonDto;
import cn.i4.data.cloud.core.entity.model.MenuButtonModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public interface IMenuButtonService extends BaseService<MenuButtonModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<MenuButtonView> selectPage(MenuButtonDto dto);

    /**
     * 根据userId获取权限下菜单的列表
     * @param userId
     * @return
     */
    List<MenuButtonView> getMenuButtonByUserId(Integer userId);

    /**
     * 根据userId获取权限下菜单的树结构
     * @param userId
     * @return
     */
    List<MenuButtonView> getMenuButtonTreeByUserId(Integer userId);

    /**
     * 获取所有菜单的树结构
     * @return
     */
    List<MenuButtonView> getMenuButtonTree();

    /**
     * 获取最大的排序
     * @param pid
     * @return
     */
    Integer getMaxSortByPid(Integer pid);

    /**
     * 根据pid获取菜单列表
     * @param pid
     * @return
     */
    List<MenuButtonView> getMenuButtonsByPid(Integer pid);

    /**
     * 根据角色ID获取权限菜单列表
     * @param roleId
     * @return
     */
    List<MenuButtonView>  getMenuButtonByRoleId(Integer roleId);
}
