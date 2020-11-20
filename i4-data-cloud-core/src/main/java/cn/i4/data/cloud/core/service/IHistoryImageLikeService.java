package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryImageLikeDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageLikeModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageLikeView;

import java.util.Set;

/**
* Service
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public interface IHistoryImageLikeService extends BaseService<HistoryImageLikeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryImageLikeView> selectPage(HistoryImageLikeDto dto);

    /**
     * 同步redis点赞数据
     * @param likeSet
     * @return
     */
    Integer asyncByRedis(Set<Object> likeSet);
}
