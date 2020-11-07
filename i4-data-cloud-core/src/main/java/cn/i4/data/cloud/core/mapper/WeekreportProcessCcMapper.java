package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportProcessCcDto;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessCcModel;
import cn.i4.data.cloud.core.entity.view.WeekreportProcessCcView;

/**
* Mapper
* @author wangjc
* @date 2020-11-06 09:46:07
*/
public interface WeekreportProcessCcMapper extends BaseIMapper<WeekreportProcessCcModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<WeekreportProcessCcView> selectPage(WeekreportProcessCcDto dto);

}
