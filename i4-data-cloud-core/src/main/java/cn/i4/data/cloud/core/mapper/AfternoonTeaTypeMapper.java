package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTypeDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTypeModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTypeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-13 13:51:17
*/
public interface AfternoonTeaTypeMapper extends BaseIMapper<AfternoonTeaTypeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaTypeView> selectPage(AfternoonTeaTypeDto dto);

}
