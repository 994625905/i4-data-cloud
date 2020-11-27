package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceMonthDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMonthModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMonthView;

/**
* Service
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface IAttendanceMonthService extends BaseService<AttendanceMonthModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceMonthView> selectPage(AttendanceMonthDto dto);

    /**
     * 查询汇总统计
     * @param dto
     * @return
     */
    IPage<AttendanceMonthView> selectAllLog(AttendanceMonthDto dto);

    /**
     * 月考勤核算
     * @param year
     * @param month
     * @return
     */
    Integer asyncMonth(int year,int month);

}
