package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AttendanceDto;
import cn.i4.data.cloud.core.entity.model.AttendanceModel;
import cn.i4.data.cloud.core.entity.view.AttendanceView;
import cn.i4.data.cloud.core.mapper.AttendanceMapper;
import cn.i4.data.cloud.core.service.IAttendanceService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-23 13:55:44
*/
@Service
@Transactional
public class AttendanceServiceImpl extends BaseServiceImpl<AttendanceMapper,AttendanceModel> implements IAttendanceService {

	@Autowired
	AttendanceMapper mapper;

	@Override
	public IPage<AttendanceView> selectPage(AttendanceDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<AttendanceView> selectAll(AttendanceDto dto) {
		return mapper.selectAll(dto);
	}

}
