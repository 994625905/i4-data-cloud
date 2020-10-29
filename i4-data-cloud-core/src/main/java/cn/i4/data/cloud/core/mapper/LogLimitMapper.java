package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogLimitDto;
import cn.i4.data.cloud.core.entity.model.LogLimitModel;
import cn.i4.data.cloud.core.entity.view.LogLimitView;

/**
* Mapper
* @author wangjc
* @date 2020-10-28 19:40:49
*/
public interface LogLimitMapper extends BaseIMapper<LogLimitModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogLimitView> selectPage(LogLimitDto dto);

}
