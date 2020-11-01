package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessView;

/**
* Mapper
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public interface LeaveProcessMapper extends BaseIMapper<LeaveProcessModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveProcessView> selectPage(LeaveProcessDto dto);

}
