package cn.i4.data.cloud.schedule.core;

import cn.hutool.core.date.DateUtil;
import cn.i4.data.cloud.base.constant.SystemConstant;
import cn.i4.data.cloud.base.util.DateUtils;
import cn.i4.data.cloud.core.service.IAttendanceDayService;
import cn.i4.data.cloud.core.service.IAttendanceMonthService;
import cn.i4.data.cloud.core.service.IAttendanceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 考勤的业务处理
 * @author wangjc
 * @title: AttendanceCore
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/11/25-18:20
 */
@Component
public class AttendanceCore {

    @Autowired
    private IAttendanceService iAttendanceService;
    @Autowired
    private IAttendanceDayService iAttendanceDayService;
    @Autowired
    private IAttendanceMonthService iAttendanceMonthService;

    /**
     * 根据时间段同步打卡数据记录，需要对接接口
     * @param startDate
     * @param endDate
     * @return
     */
    private Integer asyncCard(String startDate,String endDate){

        return null;
    }

    /**
     * 跟据打卡记录，计算当天的考勤记录，取最早（当天上午05点-12点）和最晚（中午14点到次日凌晨03点之前，加班发版本的情况）的一次打卡
     * @return
     */
    public Integer asyncDay(){
        String startDate = DateUtils.offsetYYYYMMDD(-1);
        String endDate = DateUtils.offsetYYYYMMDD(-1);
        return iAttendanceDayService.asyncAttendanceDay(startDate,endDate);
    }

    public Integer asyncMonth(){

        return null;
    }


}
