package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.constant.SystemConstant;
import cn.i4.data.cloud.base.util.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AttendanceDayDto;
import cn.i4.data.cloud.core.entity.model.AttendanceDayModel;
import cn.i4.data.cloud.core.entity.view.AttendanceDayView;
import cn.i4.data.cloud.core.mapper.AttendanceDayMapper;
import cn.i4.data.cloud.core.service.IAttendanceDayService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-23 13:55:44
*/
@Service
@Transactional
public class AttendanceDayServiceImpl extends BaseServiceImpl<AttendanceDayMapper,AttendanceDayModel> implements IAttendanceDayService {

	@Autowired
	AttendanceDayMapper mapper;

	@Override
	public IPage<AttendanceDayView> selectPage(AttendanceDayDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<AttendanceDayView> selectAll(AttendanceDayDto dto) {
		return mapper.selectAll(dto);
	}

	@Override
	public List<AttendanceDayModel> selectMendList(Integer userId) {
		List<AttendanceDayModel> list = this.list(new QueryWrapper<AttendanceDayModel>() {{
			eq("settle_status", SystemConstant.ATTENDANCE.SETTLE_STATUS_VALID);
			eq("work_date_type",SystemConstant.ATTENDANCE.WORK_DATE_TYPE_NORMAL);
			eq("user_id",userId);
			in("attendance_status",new Integer[]{SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_WITHOUT,SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_LATE,SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_EARLY_LEAVE});
			between("work_date", DateUtils.offsetYYYYMMDD(-32), DateUtils.offsetYYYYMMDD(0));
		}});
		return list;
	}

}
