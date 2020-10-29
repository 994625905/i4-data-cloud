package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeDto;
import cn.i4.data.cloud.core.entity.model.LogAutocodeModel;
import cn.i4.data.cloud.core.entity.view.LogAutocodeView;

/**
* Mapper
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public interface LogAutocodeMapper extends BaseIMapper<LogAutocodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogAutocodeView> selectPage(LogAutocodeDto dto);

}
