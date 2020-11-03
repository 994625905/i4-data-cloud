package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessNodeView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public interface ILeaveProcessNodeService extends BaseService<LeaveProcessNodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveProcessNodeView> selectPage(LeaveProcessNodeDto dto);

    /**
     * 根据流程Id获取流程节点日志
     * @param processId
     * @return
     */
    List<LeaveProcessNodeView> selectByProcessId(Integer processId);
}
