package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogRequestDto;
import cn.i4.data.cloud.core.entity.model.LogRequestModel;
import cn.i4.data.cloud.core.entity.view.LogRequestView;

/**
* Service
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public interface ILogRequestService extends BaseService<LogRequestModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogRequestView> selectPage(LogRequestDto dto);

}
