package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LogPermissionErrorDto;
import cn.i4.data.cloud.core.entity.model.LogPermissionErrorModel;
import cn.i4.data.cloud.core.entity.view.LogPermissionErrorView;
import cn.i4.data.cloud.core.mapper.LogPermissionErrorMapper;
import cn.i4.data.cloud.core.service.ILogPermissionErrorService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-09 16:49:56
*/
@Service
@Transactional
public class LogPermissionErrorServiceImpl extends BaseServiceImpl<LogPermissionErrorMapper,LogPermissionErrorModel> implements ILogPermissionErrorService {

	@Autowired
	LogPermissionErrorMapper mapper;

	@Override
	public IPage<LogPermissionErrorView> selectPage(LogPermissionErrorDto dto) {
    	return mapper.selectPage(dto);
    }

}
