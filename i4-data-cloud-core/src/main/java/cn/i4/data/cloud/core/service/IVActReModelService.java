package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.VActReModelDto;
import cn.i4.data.cloud.core.entity.model.VActReModelModel;
import cn.i4.data.cloud.core.entity.view.VActReModelView;

/**
* Service
* @author wangjc
* @date 2020-10-30 16:52:07
*/
public interface IVActReModelService extends BaseService<VActReModelModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<VActReModelView> selectPage(VActReModelDto dto);

}
