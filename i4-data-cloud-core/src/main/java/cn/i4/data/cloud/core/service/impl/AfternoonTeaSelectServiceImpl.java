package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaSelectDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaSelectModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;
import cn.i4.data.cloud.core.mapper.AfternoonTeaSelectMapper;
import cn.i4.data.cloud.core.service.IAfternoonTeaSelectService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-10 19:54:04
*/
@Service
@Transactional
public class AfternoonTeaSelectServiceImpl extends BaseServiceImpl<AfternoonTeaSelectMapper,AfternoonTeaSelectModel> implements IAfternoonTeaSelectService {

	@Autowired
	AfternoonTeaSelectMapper mapper;

	@Override
	public IPage<AfternoonTeaSelectView> selectPage(AfternoonTeaSelectDto dto) {
    	return mapper.selectPage(dto);
    }

}
