package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaView;

/**
* Service
* @author wangjc
* @date 2020-11-10 15:14:47
*/
public interface IAfternoonTeaService extends BaseService<AfternoonTeaModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaView> selectPage(AfternoonTeaDto dto);

}
