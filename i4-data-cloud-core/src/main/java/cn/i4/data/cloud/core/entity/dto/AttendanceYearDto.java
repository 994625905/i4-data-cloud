package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.AttendanceYearView;

/**
* Dto
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public class AttendanceYearDto extends BaseDto<AttendanceYearView> {

    private static final long serialVersionUID = 1606110944628L;

    private Integer year;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
