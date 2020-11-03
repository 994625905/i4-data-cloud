package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessNodeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public interface LeaveProcessNodeMapper extends BaseIMapper<LeaveProcessNodeModel> {

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
    List<LeaveProcessNodeView> selectByProcessId(@Param("processId") Integer processId);
}
