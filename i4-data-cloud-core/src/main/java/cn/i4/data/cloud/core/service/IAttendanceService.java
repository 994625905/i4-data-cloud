package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceDto;
import cn.i4.data.cloud.core.entity.model.AttendanceModel;
import cn.i4.data.cloud.core.entity.view.AttendanceView;

/**
* Service
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface IAttendanceService extends BaseService<AttendanceModel> {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<AttendanceView> selectPage(AttendanceDto dto);

    /**
     * 查询所有
     * @param dto
     * @return
     */
    IPage<AttendanceView> selectAll(AttendanceDto dto);

}
