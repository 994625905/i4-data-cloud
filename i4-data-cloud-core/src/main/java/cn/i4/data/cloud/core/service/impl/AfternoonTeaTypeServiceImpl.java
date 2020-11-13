package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTypeDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTypeModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTypeView;
import cn.i4.data.cloud.core.mapper.AfternoonTeaTypeMapper;
import cn.i4.data.cloud.core.service.IAfternoonTeaTypeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-13 13:51:17
*/
@Service
@Transactional
public class AfternoonTeaTypeServiceImpl extends BaseServiceImpl<AfternoonTeaTypeMapper,AfternoonTeaTypeModel> implements IAfternoonTeaTypeService {

	@Autowired
	AfternoonTeaTypeMapper mapper;

	@Override
	public IPage<AfternoonTeaTypeView> selectPage(AfternoonTeaTypeDto dto) {
    	return mapper.selectPage(dto);
    }

}
