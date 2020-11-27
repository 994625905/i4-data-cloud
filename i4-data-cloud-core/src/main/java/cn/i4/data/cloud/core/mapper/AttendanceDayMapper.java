package cn.i4.data.cloud.core.mapper;

import java.util.List;

import cn.i4.data.cloud.core.entity.model.AttendanceMonthModel;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceDayDto;
import cn.i4.data.cloud.core.entity.model.AttendanceDayModel;
import cn.i4.data.cloud.core.entity.view.AttendanceDayView;
import org.springframework.cglib.beans.BeanMap;

/**
* Mapper
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface AttendanceDayMapper extends BaseIMapper<AttendanceDayModel> {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<AttendanceDayView> selectPage(AttendanceDayDto dto);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<AttendanceDayView> selectAll(AttendanceDayDto dto);

    /**
     * 统一校对核算
     * @param beanMap
     * @return
     */
    Integer changeStatusAll(BeanMap beanMap);

    /**
     * 根据年月查询，userId分组
     * @param yearMonth
     * @return
     */
    List<AttendanceMonthModel> selectByYearMonthGroupUser(@Param("yearMonth") String yearMonth);
}
