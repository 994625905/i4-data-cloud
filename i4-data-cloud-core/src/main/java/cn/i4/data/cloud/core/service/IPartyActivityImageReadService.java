package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageReadDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageReadModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageReadView;

import java.util.Set;

/**
* Service
* @author wangjc
* @date 2020-11-18 10:11:37
*/
public interface IPartyActivityImageReadService extends BaseService<PartyActivityImageReadModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityImageReadView> selectPage(PartyActivityImageReadDto dto);

    /**
     * 同步redis中数据
     * @param set
     * @return
     */
    Integer asyncByRedis(Set<Object> set);
}
