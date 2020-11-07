package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportDto;
import cn.i4.data.cloud.core.entity.model.WeekreportModel;
import cn.i4.data.cloud.core.entity.view.WeekreportView;

/**
* Mapper
* @author wangjc
* @date 2020-11-06 09:46:07
*/
public interface WeekreportMapper extends BaseIMapper<WeekreportModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<WeekreportView> selectPage(WeekreportDto dto);

}
