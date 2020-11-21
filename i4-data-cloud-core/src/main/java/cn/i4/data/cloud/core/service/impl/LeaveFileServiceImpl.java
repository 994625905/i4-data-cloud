package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LeaveFileDto;
import cn.i4.data.cloud.core.entity.model.LeaveFileModel;
import cn.i4.data.cloud.core.entity.view.LeaveFileView;
import cn.i4.data.cloud.core.mapper.LeaveFileMapper;
import cn.i4.data.cloud.core.service.ILeaveFileService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-20 15:07:56
*/
@Service
@Transactional
public class LeaveFileServiceImpl extends BaseServiceImpl<LeaveFileMapper,LeaveFileModel> implements ILeaveFileService {

	@Autowired
	LeaveFileMapper mapper;

	@Override
	public IPage<LeaveFileView> selectPage(LeaveFileDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<LeaveFileView> selectByLeaveId(Integer leaveId) {
		return mapper.selectByLeaveId(leaveId);
	}

}
