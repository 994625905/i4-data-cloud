package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AutocodeDatasourceDto;
import cn.i4.data.cloud.core.entity.model.AutocodeDatasourceModel;
import cn.i4.data.cloud.core.entity.view.AutocodeDatasourceView;
import cn.i4.data.cloud.core.mapper.AutocodeDatasourceMapper;
import cn.i4.data.cloud.core.service.IAutocodeDatasourceService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-25 17:37:12
*/
@Service
@Transactional
public class AutocodeDatasourceServiceImpl extends BaseServiceImpl<AutocodeDatasourceMapper,AutocodeDatasourceModel> implements IAutocodeDatasourceService {

	@Autowired
	AutocodeDatasourceMapper mapper;

	@Override
	public IPage<AutocodeDatasourceView> selectPage(AutocodeDatasourceDto dto) {
    	return mapper.selectPage(dto);
    }

}
