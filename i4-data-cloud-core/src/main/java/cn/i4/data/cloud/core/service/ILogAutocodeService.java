package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeDto;
import cn.i4.data.cloud.core.entity.model.LogAutocodeModel;
import cn.i4.data.cloud.core.entity.view.LogAutocodeView;

/**
* Service
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public interface ILogAutocodeService extends BaseService<LogAutocodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogAutocodeView> selectPage(LogAutocodeDto dto);

}
