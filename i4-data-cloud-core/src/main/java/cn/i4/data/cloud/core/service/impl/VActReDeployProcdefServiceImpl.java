package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.VActReDeployProcdefDto;
import cn.i4.data.cloud.core.entity.model.VActReDeployProcdefModel;
import cn.i4.data.cloud.core.entity.view.VActReDeployProcdefView;
import cn.i4.data.cloud.core.mapper.VActReDeployProcdefMapper;
import cn.i4.data.cloud.core.service.IVActReDeployProcdefService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-30 16:52:07
*/
@Service
@Transactional
public class VActReDeployProcdefServiceImpl extends BaseServiceImpl<VActReDeployProcdefMapper,VActReDeployProcdefModel> implements IVActReDeployProcdefService {

	@Autowired
	VActReDeployProcdefMapper mapper;

	@Override
	public IPage<VActReDeployProcdefView> selectPage(VActReDeployProcdefDto dto) {
    	return mapper.selectPage(dto);
    }

}
