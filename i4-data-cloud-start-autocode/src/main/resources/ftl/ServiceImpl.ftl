package cn.i4.data.cloud.${ModuleName}.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.${ModuleName}.entity.dto.${ModelName}Dto;
import cn.i4.data.cloud.${ModuleName}.entity.model.${ModelName}Model;
import cn.i4.data.cloud.${ModuleName}.entity.view.${ModelName}View;
import cn.i4.data.cloud.${ModuleName}.mapper.${ModelName}Mapper;
import cn.i4.data.cloud.${ModuleName}.service.I${ModelName}Service;
import org.springframework.transaction.annotation.Transactional;

/**
* ${table_Comment}Service Impl
* @author ${author}
* @date ${crateDate}
*/
@Service
@Transactional
public class ${ModelName}ServiceImpl extends BaseServiceImpl<${ModelName}Mapper,${ModelName}Model> implements I${ModelName}Service {

	@Autowired
	${ModelName}Mapper mapper;

	@Override
	public IPage<${ModelName}View> selectPage(${ModelName}Dto dto) {
    	return mapper.selectPage(dto);
    }

}
