package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AttendanceCalendarModel;
import cn.i4.data.cloud.core.entity.view.AttendanceCalendarView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-24 20:43:55
*/
public class AttendanceCalendarDto extends BaseDto<AttendanceCalendarView> {

    private static final long serialVersionUID = 1606221835436L;

    private Integer id;

    private Integer year;

    private Integer type;

    private List<AttendanceCalendarModel> calendarList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<AttendanceCalendarModel> getCalendarList() {
        return calendarList;
    }

    public void setCalendarList(List<AttendanceCalendarModel> calendarList) {
        this.calendarList = calendarList;
    }
}
