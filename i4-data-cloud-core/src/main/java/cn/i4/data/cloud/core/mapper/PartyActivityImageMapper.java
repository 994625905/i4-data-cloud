package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageView;

/**
* Mapper
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public interface PartyActivityImageMapper extends BaseIMapper<PartyActivityImageModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityImageView> selectPage(PartyActivityImageDto dto);

}
