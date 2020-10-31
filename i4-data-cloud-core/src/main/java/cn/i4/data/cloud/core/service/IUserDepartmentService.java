package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.UserDepartmentDto;
import cn.i4.data.cloud.core.entity.model.UserDepartmentModel;
import cn.i4.data.cloud.core.entity.view.UserDepartmentView;

/**
* Service
* @author wangjc
* @date 2020-10-31 16:11:56
*/
public interface IUserDepartmentService extends BaseService<UserDepartmentModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<UserDepartmentView> selectPage(UserDepartmentDto dto);

    /**
     * 设置用户角色
     * @param userId
     * @param departmentId
     * @return
     * @throws CommonException
     */
    Boolean changeDepartment(Integer userId, Integer departmentId) throws CommonException;
}
