package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LogRequestDto;
import cn.i4.data.cloud.core.entity.model.LogRequestModel;
import cn.i4.data.cloud.core.entity.view.LogRequestView;
import cn.i4.data.cloud.core.mapper.LogRequestMapper;
import cn.i4.data.cloud.core.service.ILogRequestService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-25 17:37:12
*/
@Service
@Transactional
public class LogRequestServiceImpl extends BaseServiceImpl<LogRequestMapper,LogRequestModel> implements ILogRequestService {

	@Autowired
	LogRequestMapper mapper;

	@Override
	public IPage<LogRequestView> selectPage(LogRequestDto dto) {
    	return mapper.selectPage(dto);
    }

}
