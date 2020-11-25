package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendProcessNodeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface AttendanceMendProcessNodeMapper extends BaseIMapper<AttendanceMendProcessNodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceMendProcessNodeView> selectPage(AttendanceMendProcessNodeDto dto);

    /**
     * 查询流程办理的节点
     * @param processId
     * @return
     */
    List<AttendanceMendProcessNodeView> selectByProcessId(@Param("processId") Integer processId);
}
