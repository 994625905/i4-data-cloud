package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.WeekreportProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.WeekreportProcessNodeView;
import cn.i4.data.cloud.core.mapper.WeekreportProcessNodeMapper;
import cn.i4.data.cloud.core.service.IWeekreportProcessNodeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-06 09:46:07
*/
@Service
@Transactional
public class WeekreportProcessNodeServiceImpl extends BaseServiceImpl<WeekreportProcessNodeMapper,WeekreportProcessNodeModel> implements IWeekreportProcessNodeService {

	@Autowired
	WeekreportProcessNodeMapper mapper;

	@Override
	public IPage<WeekreportProcessNodeView> selectPage(WeekreportProcessNodeDto dto) {
    	return mapper.selectPage(dto);
    }

}
