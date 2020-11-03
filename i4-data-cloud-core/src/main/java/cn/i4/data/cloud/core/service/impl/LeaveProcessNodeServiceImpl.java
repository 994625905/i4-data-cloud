package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessNodeView;
import cn.i4.data.cloud.core.mapper.LeaveProcessNodeMapper;
import cn.i4.data.cloud.core.service.ILeaveProcessNodeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-01 14:58:27
*/
@Service
@Transactional
public class LeaveProcessNodeServiceImpl extends BaseServiceImpl<LeaveProcessNodeMapper,LeaveProcessNodeModel> implements ILeaveProcessNodeService {

	@Autowired
	LeaveProcessNodeMapper mapper;

	@Override
	public IPage<LeaveProcessNodeView> selectPage(LeaveProcessNodeDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<LeaveProcessNodeView> selectByProcessId(Integer processId) {
		List<LeaveProcessNodeView> list = mapper.selectByProcessId(processId);
		return list;
	}

}
