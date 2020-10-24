package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.RoleDto;
import cn.i4.data.cloud.core.entity.model.RoleModel;
import cn.i4.data.cloud.core.entity.view.RoleView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public interface IRoleService extends BaseService<RoleModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<RoleView> selectPage(RoleDto dto);

    /**
     * 根据用户ID查所拥有的的角色
     * @param userId
     * @return
     */
    List<RoleView> selectRolesByUserId(Integer userId);

    /**
     * 根据角色ID查所拥有的的角色
     * @param roleIds
     * @return
     */
    List<RoleModel> selectRolesByIds(List<Integer> roleIds);

}
