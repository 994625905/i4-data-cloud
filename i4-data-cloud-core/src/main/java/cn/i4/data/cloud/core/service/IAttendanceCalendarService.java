package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceCalendarDto;
import cn.i4.data.cloud.core.entity.model.AttendanceCalendarModel;
import cn.i4.data.cloud.core.entity.view.AttendanceCalendarView;

/**
* Service
* @author wangjc
* @date 2020-11-24 20:43:55
*/
public interface IAttendanceCalendarService extends BaseService<AttendanceCalendarModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceCalendarView> selectPage(AttendanceCalendarDto dto);

    /**
     * 新增日志设置
     * @param dto
     * @return
     */
    Boolean insert(AttendanceCalendarDto dto) throws CommonException;
}
