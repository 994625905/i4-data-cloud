package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqQueueDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqQueueView;
import cn.i4.data.cloud.core.mapper.SetRabbitmqQueueMapper;
import cn.i4.data.cloud.core.service.ISetRabbitmqQueueService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-24 13:57:41
*/
@Service
@Transactional
public class SetRabbitmqQueueServiceImpl extends BaseServiceImpl<SetRabbitmqQueueMapper,SetRabbitmqQueueModel> implements ISetRabbitmqQueueService {

	@Autowired
	SetRabbitmqQueueMapper mapper;

	@Override
	public IPage<SetRabbitmqQueueView> selectPage(SetRabbitmqQueueDto dto) {
    	return mapper.selectPage(dto);
    }

}
