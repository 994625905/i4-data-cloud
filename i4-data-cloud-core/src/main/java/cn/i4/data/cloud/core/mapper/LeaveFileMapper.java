package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveFileDto;
import cn.i4.data.cloud.core.entity.model.LeaveFileModel;
import cn.i4.data.cloud.core.entity.view.LeaveFileView;

/**
* Mapper
* @author wangjc
* @date 2020-11-20 15:07:56
*/
public interface LeaveFileMapper extends BaseIMapper<LeaveFileModel> {

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
    List<LeaveFileView> selectByLeaveId(@Param("leaveId") Integer leaveId);
}
