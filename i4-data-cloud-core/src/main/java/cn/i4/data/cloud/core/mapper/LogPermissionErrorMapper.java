package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogPermissionErrorDto;
import cn.i4.data.cloud.core.entity.model.LogPermissionErrorModel;
import cn.i4.data.cloud.core.entity.view.LogPermissionErrorView;

/**
* Mapper
* @author wangjc
* @date 2020-11-09 16:49:56
*/
public interface LogPermissionErrorMapper extends BaseIMapper<LogPermissionErrorModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogPermissionErrorView> selectPage(LogPermissionErrorDto dto);

}
