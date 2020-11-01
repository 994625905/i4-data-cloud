package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessView;
import cn.i4.data.cloud.core.mapper.LeaveProcessMapper;
import cn.i4.data.cloud.core.service.ILeaveProcessService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-01 14:58:27
*/
@Service
@Transactional
public class LeaveProcessServiceImpl extends BaseServiceImpl<LeaveProcessMapper,LeaveProcessModel> implements ILeaveProcessService {

	@Autowired
	LeaveProcessMapper mapper;

	@Override
	public IPage<LeaveProcessView> selectPage(LeaveProcessDto dto) {
    	return mapper.selectPage(dto);
    }

}
