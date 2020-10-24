package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.SystemConstantDto;
import cn.i4.data.cloud.core.entity.model.SystemConstantModel;
import cn.i4.data.cloud.core.entity.view.SystemConstantView;

/**
* Mapper
* @author wangjc
* @date 2020-10-14 14:43:30
*/
public interface SystemConstantMapper extends BaseIMapper<SystemConstantModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<SystemConstantView> selectPage(SystemConstantDto dto);

}
