package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendProcessDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendProcessModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendProcessView;
import cn.i4.data.cloud.core.mapper.AttendanceMendProcessMapper;
import cn.i4.data.cloud.core.service.IAttendanceMendProcessService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-23 13:55:44
*/
@Service
@Transactional
public class AttendanceMendProcessServiceImpl extends BaseServiceImpl<AttendanceMendProcessMapper,AttendanceMendProcessModel> implements IAttendanceMendProcessService {

	@Autowired
	AttendanceMendProcessMapper mapper;

	@Override
	public IPage<AttendanceMendProcessView> selectPage(AttendanceMendProcessDto dto) {
    	return mapper.selectPage(dto);
    }

}
