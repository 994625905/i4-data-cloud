package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveTypeDto;
import cn.i4.data.cloud.core.entity.model.LeaveTypeModel;
import cn.i4.data.cloud.core.entity.view.LeaveTypeView;

/**
* Service
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public interface ILeaveTypeService extends BaseService<LeaveTypeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveTypeView> selectPage(LeaveTypeDto dto);

}
