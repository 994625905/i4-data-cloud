package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageLikeDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageLikeModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageLikeView;

import java.util.Set;

/**
* Service
* @author wangjc
* @date 2020-11-18 10:11:37
*/
public interface IPartyActivityImageLikeService extends BaseService<PartyActivityImageLikeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityImageLikeView> selectPage(PartyActivityImageLikeDto dto);

    /**
     * 同步redis数据
     * @param set
     * @return
     */
    Integer asyncByRedis(Set<Object> set);

}
