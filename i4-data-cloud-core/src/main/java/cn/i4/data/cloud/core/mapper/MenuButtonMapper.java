package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.MenuButtonDto;
import cn.i4.data.cloud.core.entity.model.MenuButtonModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;

/**
* Mapper
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public interface MenuButtonMapper extends BaseIMapper<MenuButtonModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<MenuButtonView> selectPage(MenuButtonDto dto);

    /**
     * 根据userId获取权限下的菜单
     * @param userId
     * @return
     */
    List<MenuButtonView> getMenuButtonTreeByUserId(@Param("userId") Integer userId,@Param("status") Integer status);

    /**
     * 获取所有菜单的树结构
     * @param active
     * @return
     */
    List<MenuButtonView> getMenuButtonTree(@Param("status") Integer active);

    /**
     * 获取最大的排序
     * @param pid
     * @return
     */
    Integer getMaxSortByPid(@Param("pid") Integer pid);

    /**
     * 根据pid获取菜单列表
     * @param pid
     * @return
     */
    List<MenuButtonView> getMenuButtonsByPid(@Param("pid") Integer pid);

    /**
     * 根据角色ID获取权限菜单列表
     * @param roleId
     * @return
     */
    List<MenuButtonView> getMenuButtonByRoleId(@Param("roleId") Integer roleId,@Param("status") Integer status);
}
