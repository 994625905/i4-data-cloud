package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceDto;
import cn.i4.data.cloud.core.entity.model.AttendanceModel;
import cn.i4.data.cloud.core.entity.view.AttendanceView;

/**
* Mapper
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface AttendanceMapper extends BaseIMapper<AttendanceModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceView> selectPage(AttendanceDto dto);

    /**
     * 查询所有
     * @param dto
     * @return
     */
    IPage<AttendanceView> selectAll(AttendanceDto dto);

    /**
     * 查询上班的打卡记录（每个用户取最早的一条）
     * @param date
     * @param start
     * @param end
     * @return
     */
    List<AttendanceView> selectByStartWork(@Param("date") String date, @Param("next") String next, @Param("start") Long start, @Param("end") Long end);

    /**
     * 查询下班的打卡记录（每个用户取最迟的一条）
     * @param date
     * @param next
     * @param start
     * @param end
     * @return
     */
    List<AttendanceView> selectByEndWork(@Param("date") String date, @Param("next") String next, @Param("start") Long start, @Param("end") Long end);

}
