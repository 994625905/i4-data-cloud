package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.VActReModelDto;
import cn.i4.data.cloud.core.entity.model.VActReModelModel;
import cn.i4.data.cloud.core.entity.view.VActReModelView;
import cn.i4.data.cloud.core.mapper.VActReModelMapper;
import cn.i4.data.cloud.core.service.IVActReModelService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-30 16:52:07
*/
@Service
@Transactional
public class VActReModelServiceImpl extends BaseServiceImpl<VActReModelMapper,VActReModelModel> implements IVActReModelService {

	@Autowired
	VActReModelMapper mapper;

	@Override
	public IPage<VActReModelView> selectPage(VActReModelDto dto) {
    	return mapper.selectPage(dto);
    }

}
