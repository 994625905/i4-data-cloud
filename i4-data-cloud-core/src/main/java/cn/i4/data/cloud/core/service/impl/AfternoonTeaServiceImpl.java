package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaView;
import cn.i4.data.cloud.core.mapper.AfternoonTeaMapper;
import cn.i4.data.cloud.core.service.IAfternoonTeaService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-10 15:14:47
*/
@Service
@Transactional
public class AfternoonTeaServiceImpl extends BaseServiceImpl<AfternoonTeaMapper,AfternoonTeaModel> implements IAfternoonTeaService {

	@Autowired
	AfternoonTeaMapper mapper;

	@Override
	public IPage<AfternoonTeaView> selectPage(AfternoonTeaDto dto) {
    	return mapper.selectPage(dto);
    }

}
