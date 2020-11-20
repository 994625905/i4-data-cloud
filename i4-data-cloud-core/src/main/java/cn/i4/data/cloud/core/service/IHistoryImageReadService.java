package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryImageReadDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageReadModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageReadView;

import java.util.Set;

/**
* Service
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public interface IHistoryImageReadService extends BaseService<HistoryImageReadModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryImageReadView> selectPage(HistoryImageReadDto dto);

    /**
     * 同步redis中的阅读数据
     * @param readSet
     * @return
     */
    Integer asyncByRedis(Set<Object> readSet);
}
