package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AttendanceYearDto;
import cn.i4.data.cloud.core.entity.model.AttendanceYearModel;
import cn.i4.data.cloud.core.entity.view.AttendanceYearView;
import cn.i4.data.cloud.core.mapper.AttendanceYearMapper;
import cn.i4.data.cloud.core.service.IAttendanceYearService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-23 13:55:44
*/
@Service
@Transactional
public class AttendanceYearServiceImpl extends BaseServiceImpl<AttendanceYearMapper,AttendanceYearModel> implements IAttendanceYearService {

	@Autowired
	AttendanceYearMapper mapper;

	@Override
	public IPage<AttendanceYearView> selectPage(AttendanceYearDto dto) {
    	return mapper.selectPage(dto);
    }

}
