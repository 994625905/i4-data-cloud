package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.VActReDeployProcdefDto;
import cn.i4.data.cloud.core.entity.model.VActReDeployProcdefModel;
import cn.i4.data.cloud.core.entity.view.VActReDeployProcdefView;

/**
* Service
* @author wangjc
* @date 2020-10-30 16:52:07
*/
public interface IVActReDeployProcdefService extends BaseService<VActReDeployProcdefModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<VActReDeployProcdefView> selectPage(VActReDeployProcdefDto dto);

}
