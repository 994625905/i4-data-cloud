package cn.i4.data.cloud.core.service;

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


}
