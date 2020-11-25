package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendProcessDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendProcessModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendProcessView;

/**
* Service
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface IAttendanceMendProcessService extends BaseService<AttendanceMendProcessModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceMendProcessView> selectPage(AttendanceMendProcessDto dto);

}
