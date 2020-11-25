package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendProcessNodeView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface IAttendanceMendProcessNodeService extends BaseService<AttendanceMendProcessNodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceMendProcessNodeView> selectPage(AttendanceMendProcessNodeDto dto);

    /**
     * 查询流程节点
     * @param processId
     * @return
     */
    List<AttendanceMendProcessNodeView> selectByProcessId(Integer processId);

}
