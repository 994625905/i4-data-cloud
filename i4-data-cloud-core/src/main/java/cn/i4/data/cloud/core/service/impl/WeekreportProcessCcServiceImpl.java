package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.WeekreportProcessCcDto;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessCcModel;
import cn.i4.data.cloud.core.entity.view.WeekreportProcessCcView;
import cn.i4.data.cloud.core.mapper.WeekreportProcessCcMapper;
import cn.i4.data.cloud.core.service.IWeekreportProcessCcService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-06 09:46:07
*/
@Service
@Transactional
public class WeekreportProcessCcServiceImpl extends BaseServiceImpl<WeekreportProcessCcMapper,WeekreportProcessCcModel> implements IWeekreportProcessCcService {

	@Autowired
	WeekreportProcessCcMapper mapper;

	@Override
	public IPage<WeekreportProcessCcView> selectPage(WeekreportProcessCcDto dto) {
    	return mapper.selectPage(dto);
    }

}
