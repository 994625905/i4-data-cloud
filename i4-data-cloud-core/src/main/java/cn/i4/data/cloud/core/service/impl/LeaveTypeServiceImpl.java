package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LeaveTypeDto;
import cn.i4.data.cloud.core.entity.model.LeaveTypeModel;
import cn.i4.data.cloud.core.entity.view.LeaveTypeView;
import cn.i4.data.cloud.core.mapper.LeaveTypeMapper;
import cn.i4.data.cloud.core.service.ILeaveTypeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-01 14:58:27
*/
@Service
@Transactional
public class LeaveTypeServiceImpl extends BaseServiceImpl<LeaveTypeMapper,LeaveTypeModel> implements ILeaveTypeService {

	@Autowired
	LeaveTypeMapper mapper;

	@Override
	public IPage<LeaveTypeView> selectPage(LeaveTypeDto dto) {
    	return mapper.selectPage(dto);
    }

}
