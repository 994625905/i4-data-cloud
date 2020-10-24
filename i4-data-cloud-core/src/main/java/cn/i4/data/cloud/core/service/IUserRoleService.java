package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.UserRoleDto;
import cn.i4.data.cloud.core.entity.model.UserRoleModel;
import cn.i4.data.cloud.core.entity.view.UserRoleView;

/**
* Service
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public interface IUserRoleService extends BaseService<UserRoleModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<UserRoleView> selectPage(UserRoleDto dto);

    /**
     * 根据邀请码ID设置用户角色
     * @param userId
     * @param inviteCodeId
     * @return
     */
    Boolean setUserRoleByInviteCodeId(Integer userId, Integer inviteCodeId);
}
