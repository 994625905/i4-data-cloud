package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageReadDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageReadModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageReadView;

/**
* Mapper
* @author wangjc
* @date 2020-11-18 10:11:37
*/
public interface PartyActivityImageReadMapper extends BaseIMapper<PartyActivityImageReadModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityImageReadView> selectPage(PartyActivityImageReadDto dto);

}
