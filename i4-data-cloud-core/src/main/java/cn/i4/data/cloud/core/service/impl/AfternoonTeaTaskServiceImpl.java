package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;
import cn.i4.data.cloud.core.mapper.AfternoonTeaTaskMapper;
import cn.i4.data.cloud.core.service.IAfternoonTeaTaskService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-10 19:54:04
*/
@Service
@Transactional
public class AfternoonTeaTaskServiceImpl extends BaseServiceImpl<AfternoonTeaTaskMapper,AfternoonTeaTaskModel> implements IAfternoonTeaTaskService {

	@Autowired
	AfternoonTeaTaskMapper mapper;

	@Override
	public IPage<AfternoonTeaTaskView> selectPage(AfternoonTeaTaskDto dto) {
    	return mapper.selectPage(dto);
    }

}
