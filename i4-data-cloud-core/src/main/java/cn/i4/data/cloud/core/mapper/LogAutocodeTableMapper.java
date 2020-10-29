package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeTableDto;
import cn.i4.data.cloud.core.entity.model.LogAutocodeTableModel;
import cn.i4.data.cloud.core.entity.view.LogAutocodeTableView;

/**
* Mapper
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public interface LogAutocodeTableMapper extends BaseIMapper<LogAutocodeTableModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogAutocodeTableView> selectPage(LogAutocodeTableDto dto);

}
