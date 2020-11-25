package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendView;

/**
* Service
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface IAttendanceMendService extends BaseService<AttendanceMendModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceMendView> selectPage(AttendanceMendDto dto);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<AttendanceMendView> selectAllLog(AttendanceMendDto dto);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    AttendanceMendView selectById(Integer id);
}
