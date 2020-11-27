package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceDayDto;
import cn.i4.data.cloud.core.entity.model.AttendanceDayModel;
import cn.i4.data.cloud.core.entity.view.AttendanceDayView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface IAttendanceDayService extends BaseService<AttendanceDayModel> {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<AttendanceDayView> selectPage(AttendanceDayDto dto);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<AttendanceDayView> selectAll(AttendanceDayDto dto);

    /**
     * 加载需要补卡的工作日
     * @param userId
     * @return
     */
    List<AttendanceDayModel> selectMendList(Integer userId);

    /**
     * 统一校对核算
     * @param dto
     * @return
     * @throws CommonException
     */
    Integer changeStatusAll(AttendanceDayDto dto) throws CommonException;

    /**
     * 同步考勤（正常为一天一次，考虑到日期区间的情况）
     * @param startDate：开始时间
     * @param endDate：结束时间
     * @return
     */
    Integer asyncAttendanceDay(String startDate,String endDate);

}
