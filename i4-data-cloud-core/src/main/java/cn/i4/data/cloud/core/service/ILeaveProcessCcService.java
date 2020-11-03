package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessCcDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessCcModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessCcView;

/**
* Service
* @author wangjc
* @date 2020-11-03 18:13:38
*/
public interface ILeaveProcessCcService extends BaseService<LeaveProcessCcModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveProcessCcView> selectPage(LeaveProcessCcDto dto);

}
