package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveDto;
import cn.i4.data.cloud.core.entity.model.LeaveModel;
import cn.i4.data.cloud.core.entity.view.LeaveView;

/**
* Service
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public interface ILeaveService extends BaseService<LeaveModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveView> selectPage(LeaveDto dto);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<LeaveView> selectAllLog(LeaveDto dto);
}
