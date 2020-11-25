package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AttendanceCalendarDto;
import cn.i4.data.cloud.core.entity.model.AttendanceCalendarModel;
import cn.i4.data.cloud.core.entity.view.AttendanceCalendarView;
import cn.i4.data.cloud.core.mapper.AttendanceCalendarMapper;
import cn.i4.data.cloud.core.service.IAttendanceCalendarService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-24 20:43:55
*/
@Service
@Transactional
public class AttendanceCalendarServiceImpl extends BaseServiceImpl<AttendanceCalendarMapper,AttendanceCalendarModel> implements IAttendanceCalendarService {

	@Autowired
	AttendanceCalendarMapper mapper;

	@Override
	public IPage<AttendanceCalendarView> selectPage(AttendanceCalendarDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Boolean insert(AttendanceCalendarDto dto) throws CommonException {
		List<AttendanceCalendarModel> calendarList = dto.getCalendarList();
		int insert = 0;

		/** 遍历新增 */
		for(AttendanceCalendarModel model:calendarList){
			model.setCreateTime(System.currentTimeMillis()/1000L);
			model.setCreateUserId(dto.getUserId());
			insert += this.mapper.insert(model);
		}
		if(insert != calendarList.size()){
			throw new CommonException("新增失败");
		}
		return true;
	}

}
