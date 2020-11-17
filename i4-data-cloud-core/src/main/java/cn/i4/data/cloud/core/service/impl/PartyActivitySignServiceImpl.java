package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.PartyActivitySignDto;
import cn.i4.data.cloud.core.entity.model.PartyActivitySignModel;
import cn.i4.data.cloud.core.entity.view.PartyActivitySignView;
import cn.i4.data.cloud.core.mapper.PartyActivitySignMapper;
import cn.i4.data.cloud.core.service.IPartyActivitySignService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-14 14:20:33
*/
@Service
@Transactional
public class PartyActivitySignServiceImpl extends BaseServiceImpl<PartyActivitySignMapper,PartyActivitySignModel> implements IPartyActivitySignService {

	@Autowired
	PartyActivitySignMapper mapper;

	@Override
	public IPage<PartyActivitySignView> selectPage(PartyActivitySignDto dto) {
    	return mapper.selectPage(dto);
    }

}
