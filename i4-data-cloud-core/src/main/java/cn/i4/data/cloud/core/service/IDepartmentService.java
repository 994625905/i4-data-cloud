package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.DepartmentDto;
import cn.i4.data.cloud.core.entity.model.DepartmentModel;
import cn.i4.data.cloud.core.entity.view.DepartmentView;

/**
* Service
* @author wangjc
* @date 2020-10-31 15:24:09
*/
public interface IDepartmentService extends BaseService<DepartmentModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<DepartmentView> selectPage(DepartmentDto dto);

    /**
     * 根据用户id获取所属的部门
     * @param userId
     * @return
     */
    DepartmentView getByUserId(Integer userId);
}
