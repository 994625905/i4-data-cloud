package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.hutool.core.convert.Convert;
import cn.i4.data.cloud.base.constant.SystemConstant;
import cn.i4.data.cloud.base.util.DateUtils;
import cn.i4.data.cloud.core.mapper.AttendanceDayMapper;
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
	@Autowired
	AttendanceDayMapper dayMapper;

	@Override
	public IPage<AttendanceMonthView> selectPage(AttendanceMonthDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<AttendanceMonthView> selectAllLog(AttendanceMonthDto dto) {
		return mapper.selectAllLog(dto);
	}

	@Override
	public Integer asyncMonth(int year, int month) {
		String yearMonth = year + (month > 10?""+month:"0"+month);

		/** 查询核心数据结果集 */
		List<AttendanceMonthModel> list = dayMapper.selectByYearMonthGroupUser(yearMonth);

		/** 变量新增 */
		int insert = 0;
		for(AttendanceMonthModel model:list){
			model.setYear(year);//设置年
			model.setMonth(month);//设置月
			model.setUpdateStatus(SystemConstant.ATTENDANCE.UPDATE_STATUS_AUTO);

			/** 核算请假的有效小时，还是不核算了，留给人事自己计算 */
			insert += this.mapper.insert(model);
		}
		return insert;
	}

}
