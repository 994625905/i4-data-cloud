package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceMonthDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMonthModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMonthView;

/**
* Mapper
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface AttendanceMonthMapper extends BaseIMapper<AttendanceMonthModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceMonthView> selectPage(AttendanceMonthDto dto);

    /**
     * 查询汇总记录
     * @param dto
     * @return
     */
    IPage<AttendanceMonthView> selectAllLog(AttendanceMonthDto dto);

}
