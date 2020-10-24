package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqQueueDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqQueueView;

/**
* Service
* @author wangjc
* @date 2020-10-24 13:57:41
*/
public interface ISetRabbitmqQueueService extends BaseService<SetRabbitmqQueueModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<SetRabbitmqQueueView> selectPage(SetRabbitmqQueueDto dto);

}
