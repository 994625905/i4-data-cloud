package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqExchangeDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqExchangeModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqExchangeView;

/**
* Service
* @author wangjc
* @date 2020-10-24 13:57:40
*/
public interface ISetRabbitmqExchangeService extends BaseService<SetRabbitmqExchangeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<SetRabbitmqExchangeView> selectPage(SetRabbitmqExchangeDto dto);

}
