package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeTypeDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeTypeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeTypeView;

/**
* Service
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public interface IHistoryNoticeTypeService extends BaseService<HistoryNoticeTypeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryNoticeTypeView> selectPage(HistoryNoticeTypeDto dto);

}
