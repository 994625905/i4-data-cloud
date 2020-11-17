package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityTypeDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityTypeModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityTypeView;

/**
* Service
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public interface IPartyActivityTypeService extends BaseService<PartyActivityTypeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityTypeView> selectPage(PartyActivityTypeDto dto);

}
