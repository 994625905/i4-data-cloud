package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeDto;
import cn.i4.data.cloud.core.entity.model.LogAutocodeModel;
import cn.i4.data.cloud.core.entity.view.LogAutocodeView;
import cn.i4.data.cloud.core.mapper.LogAutocodeMapper;
import cn.i4.data.cloud.core.service.ILogAutocodeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-25 17:37:12
*/
@Service
@Transactional
public class LogAutocodeServiceImpl extends BaseServiceImpl<LogAutocodeMapper,LogAutocodeModel> implements ILogAutocodeService {

	@Autowired
	LogAutocodeMapper mapper;

	@Override
	public IPage<LogAutocodeView> selectPage(LogAutocodeDto dto) {
    	return mapper.selectPage(dto);
    }

}
