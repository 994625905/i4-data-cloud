package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AutocodeDatasourceDto;
import cn.i4.data.cloud.core.entity.model.AutocodeDatasourceModel;
import cn.i4.data.cloud.core.entity.view.AutocodeDatasourceView;

/**
* Service
* @author wangjc
* @date 2020-10-11 13:42:24
*/
public interface IAutocodeDatasourceService extends BaseService<AutocodeDatasourceModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AutocodeDatasourceView> selectPage(AutocodeDatasourceDto dto);

}
