package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LogAutocodeTableDto;
import cn.i4.data.cloud.core.entity.model.LogAutocodeTableModel;
import cn.i4.data.cloud.core.entity.view.LogAutocodeTableView;
import cn.i4.data.cloud.core.mapper.LogAutocodeTableMapper;
import cn.i4.data.cloud.core.service.ILogAutocodeTableService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-25 17:37:12
*/
@Service
@Transactional
public class LogAutocodeTableServiceImpl extends BaseServiceImpl<LogAutocodeTableMapper,LogAutocodeTableModel> implements ILogAutocodeTableService {

	@Autowired
	LogAutocodeTableMapper mapper;

	@Override
	public IPage<LogAutocodeTableView> selectPage(LogAutocodeTableDto dto) {
    	return mapper.selectPage(dto);
    }

}
