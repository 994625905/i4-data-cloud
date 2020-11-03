package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessCcDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessCcModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessCcView;

/**
* Mapper
* @author wangjc
* @date 2020-11-03 18:13:38
*/
public interface LeaveProcessCcMapper extends BaseIMapper<LeaveProcessCcModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LeaveProcessCcView> selectPage(LeaveProcessCcDto dto);

}
