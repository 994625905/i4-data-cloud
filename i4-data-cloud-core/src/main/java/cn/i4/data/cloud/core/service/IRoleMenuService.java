package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.RoleMenuDto;
import cn.i4.data.cloud.core.entity.model.RoleMenuModel;
import cn.i4.data.cloud.core.entity.view.RoleMenuView;

/**
* Service
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public interface IRoleMenuService extends BaseService<RoleMenuModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<RoleMenuView> selectPage(RoleMenuDto dto);

}
