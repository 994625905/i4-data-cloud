package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageView;

/**
* Service
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public interface IPartyActivityImageService extends BaseService<PartyActivityImageModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityImageView> selectPage(PartyActivityImageDto dto);

}
