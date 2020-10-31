package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import cn.i4.data.cloud.core.entity.view.DepartmentView;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.InviteCodeDepartmentDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeDepartmentModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeDepartmentView;

/**
* Service
* @author wangjc
* @date 2020-10-31 17:10:53
*/
public interface IInviteCodeDepartmentService extends BaseService<InviteCodeDepartmentModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<InviteCodeDepartmentView> selectPage(InviteCodeDepartmentDto dto);

    /**
     *
     * @param departmentId
     * @return
     */
    DepartmentView selectDeploymentByid(Integer departmentId);
}
