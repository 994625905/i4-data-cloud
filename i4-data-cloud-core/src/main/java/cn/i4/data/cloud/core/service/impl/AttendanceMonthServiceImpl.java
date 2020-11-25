package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AttendanceMonthDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMonthModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMonthView;
import cn.i4.data.cloud.core.mapper.AttendanceMonthMapper;
import cn.i4.data.cloud.core.service.IAttendanceMonthService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-23 13:55:44
*/
@Service
@Transactional
public class AttendanceMonthServiceImpl extends BaseServiceImpl<AttendanceMonthMapper,AttendanceMonthModel> implements IAttendanceMonthService {

	@Autowired
	AttendanceMonthMapper mapper;

	@Override
	public IPage<AttendanceMonthView> selectPage(AttendanceMonthDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<AttendanceMonthView> selectAllLog(AttendanceMonthDto dto) {
		return mapper.selectAllLog(dto);
	}

}
