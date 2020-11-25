package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.AttendanceMonthView;

/**
* Dto
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public class AttendanceMonthDto extends BaseDto<AttendanceMonthView> {

    private static final long serialVersionUID = 1606110944615L;

    private Integer year;

    private Integer month;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
