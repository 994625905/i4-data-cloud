package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTypeDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTypeModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTypeView;

/**
* Service
* @author wangjc
* @date 2020-11-13 13:51:17
*/
public interface IAfternoonTeaTypeService extends BaseService<AfternoonTeaTypeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaTypeView> selectPage(AfternoonTeaTypeDto dto);

}
