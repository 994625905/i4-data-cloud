package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.InviteCodeRoleDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeRoleModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeRoleView;

/**
* Service
* @author wangjc
* @date 2020-10-18 14:34:30
*/
public interface IInviteCodeRoleService extends BaseService<InviteCodeRoleModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<InviteCodeRoleView> selectPage(InviteCodeRoleDto dto);

}
