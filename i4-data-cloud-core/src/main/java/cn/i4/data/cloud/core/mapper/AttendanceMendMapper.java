package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendView;

/**
* Mapper
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface AttendanceMendMapper extends BaseIMapper<AttendanceMendModel> {

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<AttendanceMendView> selectPage(AttendanceMendDto dto);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    IPage<AttendanceMendView> selectAllLog(AttendanceMendDto dto);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    AttendanceMendView selectByMendId(@Param("id") Integer id);
}
