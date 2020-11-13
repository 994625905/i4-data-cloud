package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaMenuDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaMenuView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-11 11:41:50
*/
public interface IAfternoonTeaMenuService extends BaseService<AfternoonTeaMenuModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaMenuView> selectPage(AfternoonTeaMenuDto dto);

    /**
     * 根据taskId获取
     * @param taskId
     * @return
     */
    List<AfternoonTeaMenuView> getListByTaskId(Integer taskId);
}
