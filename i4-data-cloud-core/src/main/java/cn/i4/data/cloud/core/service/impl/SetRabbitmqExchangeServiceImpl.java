package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqExchangeDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqExchangeModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqExchangeView;
import cn.i4.data.cloud.core.mapper.SetRabbitmqExchangeMapper;
import cn.i4.data.cloud.core.service.ISetRabbitmqExchangeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-24 13:57:40
*/
@Service
@Transactional
public class SetRabbitmqExchangeServiceImpl extends BaseServiceImpl<SetRabbitmqExchangeMapper,SetRabbitmqExchangeModel> implements ISetRabbitmqExchangeService {

	@Autowired
	SetRabbitmqExchangeMapper mapper;

	@Override
	public IPage<SetRabbitmqExchangeView> selectPage(SetRabbitmqExchangeDto dto) {
    	return mapper.selectPage(dto);
    }

}
