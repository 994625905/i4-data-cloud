package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LogLimitDto;
import cn.i4.data.cloud.core.entity.model.LogLimitModel;
import cn.i4.data.cloud.core.entity.view.LogLimitView;
import cn.i4.data.cloud.core.mapper.LogLimitMapper;
import cn.i4.data.cloud.core.service.ILogLimitService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-28 19:40:49
*/
@Service
@Transactional
public class LogLimitServiceImpl extends BaseServiceImpl<LogLimitMapper,LogLimitModel> implements ILogLimitService {

	@Autowired
	LogLimitMapper mapper;

	@Override
	public IPage<LogLimitView> selectPage(LogLimitDto dto) {
    	return mapper.selectPage(dto);
    }

}
