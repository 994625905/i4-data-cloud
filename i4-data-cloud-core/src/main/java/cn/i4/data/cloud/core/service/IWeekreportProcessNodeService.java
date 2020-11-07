package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.WeekreportProcessNodeView;

/**
* Service
* @author wangjc
* @date 2020-11-06 09:46:07
*/
public interface IWeekreportProcessNodeService extends BaseService<WeekreportProcessNodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<WeekreportProcessNodeView> selectPage(WeekreportProcessNodeDto dto);

}
