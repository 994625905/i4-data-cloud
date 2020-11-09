package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogPermissionErrorDto;
import cn.i4.data.cloud.core.entity.model.LogPermissionErrorModel;
import cn.i4.data.cloud.core.entity.view.LogPermissionErrorView;

/**
* Service
* @author wangjc
* @date 2020-11-09 16:49:56
*/
public interface ILogPermissionErrorService extends BaseService<LogPermissionErrorModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogPermissionErrorView> selectPage(LogPermissionErrorDto dto);

}
