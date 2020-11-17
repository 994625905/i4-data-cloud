package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityFileDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityFileModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityFileView;

/**
* Mapper
* @author wangjc
* @date 2020-11-14 15:39:24
*/
public interface PartyActivityFileMapper extends BaseIMapper<PartyActivityFileModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityFileView> selectPage(PartyActivityFileDto dto);

}
