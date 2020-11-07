package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportProcessCcDto;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessCcModel;
import cn.i4.data.cloud.core.entity.view.WeekreportProcessCcView;

/**
* Service
* @author wangjc
* @date 2020-11-06 09:46:07
*/
public interface IWeekreportProcessCcService extends BaseService<WeekreportProcessCcModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<WeekreportProcessCcView> selectPage(WeekreportProcessCcDto dto);

}
