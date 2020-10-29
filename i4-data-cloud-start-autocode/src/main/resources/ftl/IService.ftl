package cn.i4.data.cloud.${ModuleName}.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.${ModuleName}.entity.dto.${ModelName}Dto;
import cn.i4.data.cloud.${ModuleName}.entity.model.${ModelName}Model;
import cn.i4.data.cloud.${ModuleName}.entity.view.${ModelName}View;

/**
* ${table_Comment}Service
* @author ${author}
* @date ${crateDate}
*/
public interface I${ModelName}Service extends BaseService<${ModelName}Model> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<${ModelName}View> selectPage(${ModelName}Dto dto);

}
