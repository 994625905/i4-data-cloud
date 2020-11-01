package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessView;

/**
* Service
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public interface ILeaveProcessService extends BaseService<LeaveProcessModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveProcessView> selectPage(LeaveProcessDto dto);

}
