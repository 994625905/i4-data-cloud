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

}
