package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityTypeDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityTypeModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityTypeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public interface PartyActivityTypeMapper extends BaseIMapper<PartyActivityTypeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityTypeView> selectPage(PartyActivityTypeDto dto);

}
