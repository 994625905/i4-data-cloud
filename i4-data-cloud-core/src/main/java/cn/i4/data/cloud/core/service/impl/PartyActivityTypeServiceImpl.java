package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.PartyActivityTypeDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityTypeModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityTypeView;
import cn.i4.data.cloud.core.mapper.PartyActivityTypeMapper;
import cn.i4.data.cloud.core.service.IPartyActivityTypeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-14 14:20:33
*/
@Service
@Transactional
public class PartyActivityTypeServiceImpl extends BaseServiceImpl<PartyActivityTypeMapper,PartyActivityTypeModel> implements IPartyActivityTypeService {

	@Autowired
	PartyActivityTypeMapper mapper;

	@Override
	public IPage<PartyActivityTypeView> selectPage(PartyActivityTypeDto dto) {
    	return mapper.selectPage(dto);
    }

}
