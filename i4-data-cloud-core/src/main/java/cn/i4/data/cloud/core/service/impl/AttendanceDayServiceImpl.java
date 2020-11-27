package cn.i4.data.cloud.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import cn.hutool.core.date.DateUtil;
import cn.i4.data.cloud.base.constant.SystemConstant;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.util.DateUtils;
import cn.i4.data.cloud.core.entity.model.AttendanceCalendarModel;
import cn.i4.data.cloud.core.entity.view.AttendanceView;
import cn.i4.data.cloud.core.mapper.AttendanceCalendarMapper;
import cn.i4.data.cloud.core.mapper.AttendanceMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
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
	@Autowired
	AttendanceCalendarMapper calendarMapper;
	@Autowired
	AttendanceMapper attendanceMapper;

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

	@Override
	public Integer changeStatusAll(AttendanceDayDto dto) throws CommonException {
		Integer res = this.mapper.changeStatusAll(BeanMap.create(dto));
		return res;
	}

	@Override
	public Integer asyncAttendanceDay(String startDate, String endDate) {
		List<String> list = DateUtils.betweenYYYYMMDD(startDate, endDate);
		List<AttendanceDayModel> addStart = new ArrayList<>();
		List<AttendanceDayModel> addEnd = new ArrayList<>();

		/** 查询日历设置 */
		List<AttendanceCalendarModel> calendarList = calendarMapper.selectList(new QueryWrapper<AttendanceCalendarModel>() {{
			between("date", startDate, endDate);
		}});

		for(String date:list){

			/** 正常情况startWorkList和endWorkList长度一致，因为是基于t_user做left join */
			List<AttendanceView> startWorkList = attendanceMapper.selectByStartWork(date,DateUtils.offsetYYYYMMDD(date,1), DateUtils.tempYYYYMMDDHour(date, "06"),
					DateUtils.tempYYYYMMDDHour(DateUtils.offsetYYYYMMDD(date,1), "03"));
			List<AttendanceView> endWorkList = attendanceMapper.selectByEndWork(date,DateUtils.offsetYYYYMMDD(date,1), DateUtils.tempYYYYMMDDHour(date, "06"),
					DateUtils.tempYYYYMMDDHour(DateUtils.offsetYYYYMMDD(date,1), "03"));

			/** 新增上班考勤，需要考虑，节假日周末加班的情况 */
			for(AttendanceView start:startWorkList){
				AttendanceDayModel day = new AttendanceDayModel();
				day = this.setStartValue(day,start,date,calendarList);
				addStart.add(day);
			}

			/** 新增下班考勤 */
			for(AttendanceView end:endWorkList){
				AttendanceDayModel day = new AttendanceDayModel();
				day = this.setEndValue(day,end,date,calendarList);

				/** 根据工作日类型设置：判断（-2未打卡，-1迟到，0正常，1早退），开始时间，结束时间，工作时长，加班时长 */
				if(day.getWorkDateType().equals(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_NORMAL)) { //正常工作日
					if(end.getId() == null){
						day.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_WITHOUT);//未打卡
					}else{

						/** 正常工作日内，如果打卡时间小于13点30，设置当天上午的工作时长 */
						if(end.getCreateTime() < DateUtils.tempYYYYMMDDHour(date,"13","30")){
							day.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_EARLY_LEAVE);//早退

							for(AttendanceDayModel add:addStart){
								if(add.getWorkDate().equals(date)){
									add.setWorkHour(DateUtils.diffHour(DateUtils.tempYYYYMMDDHour(date,"09"),end.getCreateTime()));//设置工作时长
									break;
								}
							}
						}else if(end.getCreateTime() < DateUtils.tempYYYYMMDDHour(date,"18","30") ){
							day.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_EARLY_LEAVE);//早退
							day.setWorkHour(DateUtils.diffHour(DateUtils.tempYYYYMMDDHour(date,"13","30"),end.getCreateTime()));//工作时长的计算（考虑到请假的情况）
						}else{
							day.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_NORMAL);//正常
							day.setWorkHour(5.0F);//正常工作时长，默认设置为5小时
						}
						day.setAttendanceId(end.getId());
					}
					day.setStartTime(DateUtils.tempYYYYMMDDHour(date,"13","30"));//开始时间13点30
					day.setEndTime(DateUtils.tempYYYYMMDDHour(date,"18","30"));//结束时间18点30

					/** 加班时长，从晚上7点开始核算 */
					if(end.getCreateTime() < DateUtils.tempYYYYMMDDHour(date,"19")){
						day.setWorkHourOver((float) ((end.getCreateTime() - DateUtils.tempYYYYMMDDHour(date,"19"))/3600));//向下取整
					}
				}else{
					if(end.getId() != null){
						day.setAttendanceId(end.getId());
						day.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_NORMAL);//正常
						/** 假期加班的结算，多情况处理 */
						for(AttendanceView start:startWorkList){
							if(start.getCreateDate().equals(date)){

								if(end.getCreateTime() < DateUtils.tempYYYYMMDDHour(date,"12")){
									// 1.在12点钟之前下班
									for(AttendanceDayModel add:addStart){
										if(add.getWorkDate().equals(date)){
											add.setWorkHourOver( DateUtils.diffHour(start.getCreateTime(),end.getCreateTime()) );
											break;
										}
									}
								}else if(start.getCreateTime() > DateUtils.tempYYYYMMDDHour(date,"13","30")){
									day.setWorkHourOver(DateUtils.diffHour(start.getCreateTime(),end.getCreateTime()));//加班时长
								}else{
									day.setWorkHourOver(DateUtils.diffHour(DateUtils.tempYYYYMMDDHour(date,"13","30"),end.getCreateTime()));//加班时长，从13点30分核算
								}
								break;
							}
						}
					}
				}
				addEnd.add(day);
			}
		}
		/** 数据入库 */
		int insert = 0;
		for (AttendanceDayModel model:addStart){
			insert += this.mapper.insert(model);
		}
		for(AttendanceDayModel model:addEnd){
			insert += this.mapper.insert(model);
		}
		return insert;
	}

	/**
	 * 设置上班的共性值
	 * @param model
	 * @param card
	 * @param date
	 * @param calendarList
	 * @return
	 */
	private AttendanceDayModel setStartValue(AttendanceDayModel model,AttendanceView card, String date,List<AttendanceCalendarModel> calendarList){

		model.setUserId(card.getUserId());
		model.setUpdateStatus(SystemConstant.ATTENDANCE.UPDATE_STATUS_AUTO);//自动统计
		model.setSettleStatus(SystemConstant.ATTENDANCE.SETTLE_STATUS_VALID);//核算有效
		model.setWorkDate(date);//工作日期
		model.setWeek(DateUtil.dayOfWeekEnum(DateUtils.parseYYYYMMDD(date)).toChinese());//周几
		model.setAttendanceStage(SystemConstant.ATTENDANCE.ATTENDANCE_STAGE_GO);//时间段，上班

		/** 判断工作日类型 */
		Boolean flag = true;
		for(AttendanceCalendarModel calendar:calendarList){
			if(date.equals(calendar.getDate())){
				if(calendar.getType().equals(SystemConstant.ATTENDANCE.CALENDAR_TYPE_HOLIDAY)){
					model.setWorkDateType(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_HOLIDAY);//法定节假日
				}else{
					model.setWorkDateType(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_NORMAL);//正常上班日（补班）
				}
				flag = false;
				break;
			}
		}
		if(!flag){
			if("星期日".equals(model.getWeek()) || "星期六".equals(model.getWeek())){
				model.setWorkDateType(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_WEEKEND);//正常周末
			}else{
				model.setWorkDateType(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_NORMAL);//正常上班日
			}
		}

		/** 根据工作日类型设置：判断（-2未打卡，-1迟到，0正常，1早退），开始时间，结束时间，工作时长，加班时长 */
		if(model.getWorkDateType().equals(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_NORMAL)){ //正常工作日
			if(card.getId() == null){
				model.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_WITHOUT);//未打卡
			}else{

				if(card.getCreateTime() <= DateUtils.tempYYYYMMDDHour(date,"09") ){
					model.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_NORMAL);//正常
				}else{
					model.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_LATE);//迟到
				}
				model.setAttendanceId(card.getId());
				model.setWorkHour(3.0F);//具体的上班时长到后置事件中重新，因为有的下班打卡在12点钟之前
			}
			model.setStartTime(DateUtils.tempYYYYMMDDHour(date,"09"));//开始时间9点
			model.setEndTime(DateUtils.tempYYYYMMDDHour(date,"12"));//结束时间12点
		}else{
			if(card.getId() != null){
				model.setAttendanceId(card.getId());
				model.setAttendanceStatus(SystemConstant.ATTENDANCE.ATTENDANCE_STATUS_NORMAL);//正常
				/** 假期加班的结算 */
				if(card.getCreateTime() < DateUtils.tempYYYYMMDDHour(date,"12")){
					model.setWorkHourOver(DateUtils.diffHour(card.getCreateTime(),DateUtils.tempYYYYMMDDHour(date,"12")));//加班时长
				}
			}
		}
		return model;
	}

	/**
	 * 设置下班的共性值，正常工作日内，如果打卡时间小于13点30，则属于未打卡
	 * @param model
	 * @param card
	 * @param date
	 * @param calendarList
	 * @return
	 */
	private AttendanceDayModel setEndValue(AttendanceDayModel model,AttendanceView card, String date,List<AttendanceCalendarModel> calendarList){

		model.setUserId(card.getUserId());
		model.setUpdateStatus(SystemConstant.ATTENDANCE.UPDATE_STATUS_AUTO);//自动统计
		model.setSettleStatus(SystemConstant.ATTENDANCE.SETTLE_STATUS_VALID);//核算有效
		model.setWorkDate(date);//工作日期
		model.setWeek(DateUtil.dayOfWeekEnum(DateUtils.parseYYYYMMDD(date)).toChinese());//周几
		model.setAttendanceStage(SystemConstant.ATTENDANCE.ATTENDANCE_STAGE_LEAVE);//时间段，下班

		/** 判断工作日类型 */
		Boolean flag = true;
		for(AttendanceCalendarModel calendar:calendarList){
			if(date.equals(calendar.getDate())){
				if(calendar.getType().equals(SystemConstant.ATTENDANCE.CALENDAR_TYPE_HOLIDAY)){
					model.setWorkDateType(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_HOLIDAY);//法定节假日
				}else{
					model.setWorkDateType(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_NORMAL);//正常上班日（补班）
				}
				flag = false;
				break;
			}
		}
		if(!flag){
			if("星期日".equals(model.getWeek()) || "星期六".equals(model.getWeek())){
				model.setWorkDateType(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_WEEKEND);//正常周末
			}else{
				model.setWorkDateType(SystemConstant.ATTENDANCE.WORK_DATE_TYPE_NORMAL);//正常上班日
			}
		}
		return model;
	}

}
