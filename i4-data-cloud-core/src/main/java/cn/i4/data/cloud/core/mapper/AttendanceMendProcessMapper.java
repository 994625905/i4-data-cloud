package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AttendanceMendProcessDto;
import cn.i4.data.cloud.core.entity.model.AttendanceMendProcessModel;
import cn.i4.data.cloud.core.entity.view.AttendanceMendProcessView;

/**
* Mapper
* @author wangjc
* @date 2020-11-23 13:55:44
*/
public interface AttendanceMendProcessMapper extends BaseIMapper<AttendanceMendProcessModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AttendanceMendProcessView> selectPage(AttendanceMendProcessDto dto);

}
