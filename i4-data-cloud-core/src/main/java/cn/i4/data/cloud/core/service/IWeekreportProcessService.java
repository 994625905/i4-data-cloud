package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportProcessDto;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessModel;
import cn.i4.data.cloud.core.entity.view.WeekreportProcessView;

/**
* Service
* @author wangjc
* @date 2020-11-06 09:46:07
*/
public interface IWeekreportProcessService extends BaseService<WeekreportProcessModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<WeekreportProcessView> selectPage(WeekreportProcessDto dto);

}
