package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeReadDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeReadModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeReadView;

/**
* Service
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public interface IHistoryNoticeReadService extends BaseService<HistoryNoticeReadModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryNoticeReadView> selectPage(HistoryNoticeReadDto dto);

}
