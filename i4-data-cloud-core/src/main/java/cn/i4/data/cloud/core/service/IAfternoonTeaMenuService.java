package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaMenuDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaMenuView;

/**
* Service
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public interface IAfternoonTeaMenuService extends BaseService<AfternoonTeaMenuModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaMenuView> selectPage(AfternoonTeaMenuDto dto);

}
