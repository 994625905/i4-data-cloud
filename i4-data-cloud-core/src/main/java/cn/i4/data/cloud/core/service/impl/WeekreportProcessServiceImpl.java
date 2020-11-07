package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.WeekreportProcessDto;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessModel;
import cn.i4.data.cloud.core.entity.view.WeekreportProcessView;
import cn.i4.data.cloud.core.mapper.WeekreportProcessMapper;
import cn.i4.data.cloud.core.service.IWeekreportProcessService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-06 09:46:07
*/
@Service
@Transactional
public class WeekreportProcessServiceImpl extends BaseServiceImpl<WeekreportProcessMapper,WeekreportProcessModel> implements IWeekreportProcessService {

	@Autowired
	WeekreportProcessMapper mapper;

	@Override
	public IPage<WeekreportProcessView> selectPage(WeekreportProcessDto dto) {
    	return mapper.selectPage(dto);
    }

}
