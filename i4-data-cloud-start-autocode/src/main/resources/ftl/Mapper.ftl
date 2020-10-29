package cn.i4.data.cloud.${ModuleName}.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.${ModuleName}.entity.dto.${ModelName}Dto;
import cn.i4.data.cloud.${ModuleName}.entity.model.${ModelName}Model;
import cn.i4.data.cloud.${ModuleName}.entity.view.${ModelName}View;

/**
* ${table_Comment}Mapper
* @author ${author}
* @date ${crateDate}
*/
public interface ${ModelName}Mapper extends BaseIMapper<${ModelName}Model> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<${ModelName}View> selectPage(${ModelName}Dto dto);

}
