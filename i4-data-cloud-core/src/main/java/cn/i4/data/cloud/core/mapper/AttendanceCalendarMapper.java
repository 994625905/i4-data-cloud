package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceCalendarDto;
import cn.i4.data.cloud.core.entity.model.AttendanceCalendarModel;
import cn.i4.data.cloud.core.entity.view.AttendanceCalendarView;

/**
* Mapper
* @author wangjc
* @date 2020-11-24 20:43:55
*/
public interface AttendanceCalendarMapper extends BaseIMapper<AttendanceCalendarModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceCalendarView> selectPage(AttendanceCalendarDto dto);

}
