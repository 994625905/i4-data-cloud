package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryImageDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageView;

/**
* Mapper
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public interface HistoryImageMapper extends BaseIMapper<HistoryImageModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryImageView> selectPage(HistoryImageDto dto);

}
