package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;

/**
* Service
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public interface IAfternoonTeaTaskService extends BaseService<AfternoonTeaTaskModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaTaskView> selectPage(AfternoonTeaTaskDto dto);

}