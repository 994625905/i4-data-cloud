package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LeaveDto;
import cn.i4.data.cloud.core.entity.model.LeaveModel;
import cn.i4.data.cloud.core.entity.view.LeaveView;
import cn.i4.data.cloud.core.mapper.LeaveMapper;
import cn.i4.data.cloud.core.service.ILeaveService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-01 14:58:27
*/
@Service
@Transactional
public class LeaveServiceImpl extends BaseServiceImpl<LeaveMapper,LeaveModel> implements ILeaveService {

	@Autowired
	LeaveMapper mapper;

	@Override
	public IPage<LeaveView> selectPage(LeaveDto dto) {
    	return mapper.selectPage(dto);
    }

}
