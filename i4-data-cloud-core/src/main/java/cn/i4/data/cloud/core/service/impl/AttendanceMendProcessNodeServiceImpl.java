package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendProcessNodeView;
import cn.i4.data.cloud.core.mapper.AttendanceMendProcessNodeMapper;
import cn.i4.data.cloud.core.service.IAttendanceMendProcessNodeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-23 13:55:44
*/
@Service
@Transactional
public class AttendanceMendProcessNodeServiceImpl extends BaseServiceImpl<AttendanceMendProcessNodeMapper,AttendanceMendProcessNodeModel> implements IAttendanceMendProcessNodeService {

	@Autowired
	AttendanceMendProcessNodeMapper mapper;

	@Override
	public IPage<AttendanceMendProcessNodeView> selectPage(AttendanceMendProcessNodeDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<AttendanceMendProcessNodeView> selectByProcessId(Integer processId) {
		List<AttendanceMendProcessNodeView> list = mapper.selectByProcessId(processId);
		return list;
	}

}
