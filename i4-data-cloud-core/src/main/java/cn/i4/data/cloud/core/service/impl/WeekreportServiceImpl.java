package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.WeekreportDto;
import cn.i4.data.cloud.core.entity.model.WeekreportModel;
import cn.i4.data.cloud.core.entity.view.WeekreportView;
import cn.i4.data.cloud.core.mapper.WeekreportMapper;
import cn.i4.data.cloud.core.service.IWeekreportService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-06 09:46:07
*/
@Service
@Transactional
public class WeekreportServiceImpl extends BaseServiceImpl<WeekreportMapper,WeekreportModel> implements IWeekreportService {

	@Autowired
	WeekreportMapper mapper;

	@Override
	public IPage<WeekreportView> selectPage(WeekreportDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public WeekreportView selectRealNameById(Integer id) {
		return mapper.selectRealNameById(id);
	}

	@Override
	public IPage<WeekreportView> selectAll(WeekreportDto dto) {
		return mapper.selectAll(dto);
	}

}
