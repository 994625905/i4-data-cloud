package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AutocodeDatasourceDto;
import cn.i4.data.cloud.core.entity.model.AutocodeDatasourceModel;
import cn.i4.data.cloud.core.entity.view.AutocodeDatasourceView;

/**
* Mapper
* @author wangjc
* @date 2020-10-11 13:42:24
*/
public interface AutocodeDatasourceMapper extends BaseIMapper<AutocodeDatasourceModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AutocodeDatasourceView> selectPage(AutocodeDatasourceDto dto);

}
