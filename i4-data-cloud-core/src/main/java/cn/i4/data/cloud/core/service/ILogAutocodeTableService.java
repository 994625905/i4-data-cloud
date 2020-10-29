package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeTableDto;
import cn.i4.data.cloud.core.entity.model.LogAutocodeTableModel;
import cn.i4.data.cloud.core.entity.view.LogAutocodeTableView;

/**
* Service
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public interface ILogAutocodeTableService extends BaseService<LogAutocodeTableModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogAutocodeTableView> selectPage(LogAutocodeTableDto dto);

}
