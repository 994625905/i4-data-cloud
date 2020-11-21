package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveFileDto;
import cn.i4.data.cloud.core.entity.model.LeaveFileModel;
import cn.i4.data.cloud.core.entity.view.LeaveFileView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-20 15:07:56
*/
public interface ILeaveFileService extends BaseService<LeaveFileModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveFileView> selectPage(LeaveFileDto dto);

    /**
     * 查询请假条附件列表
     * @param leaveId
     * @return
     */
    List<LeaveFileView> selectByLeaveId(Integer leaveId);
}
