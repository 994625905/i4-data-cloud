package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendView;
import cn.i4.data.cloud.core.mapper.AttendanceMendMapper;
import cn.i4.data.cloud.core.service.IAttendanceMendService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-23 13:55:44
*/
@Service
@Transactional
public class AttendanceMendServiceImpl extends BaseServiceImpl<AttendanceMendMapper,AttendanceMendModel> implements IAttendanceMendService {

	@Autowired
	AttendanceMendMapper mapper;

	@Override
	public IPage<AttendanceMendView> selectPage(AttendanceMendDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<AttendanceMendView> selectAllLog(AttendanceMendDto dto) {
		return mapper.selectAllLog(dto);
	}

	@Override
	public AttendanceMendView selectById(Integer id) {
		return mapper.selectByMendId(id);
	}

}
