package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceYearDto;
import cn.i4.data.cloud.core.entity.model.AttendanceYearModel;
import cn.i4.data.cloud.core.entity.view.AttendanceYearView;

/**
* Service
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface IAttendanceYearService extends BaseService<AttendanceYearModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceYearView> selectPage(AttendanceYearDto dto);

}
