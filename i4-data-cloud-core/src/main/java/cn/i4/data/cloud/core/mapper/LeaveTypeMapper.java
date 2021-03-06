package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveTypeDto;
import cn.i4.data.cloud.core.entity.model.LeaveTypeModel;
import cn.i4.data.cloud.core.entity.view.LeaveTypeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public interface LeaveTypeMapper extends BaseIMapper<LeaveTypeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveTypeView> selectPage(LeaveTypeDto dto);

}
