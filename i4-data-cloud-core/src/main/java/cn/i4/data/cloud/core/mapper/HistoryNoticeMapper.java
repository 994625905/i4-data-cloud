package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public interface HistoryNoticeMapper extends BaseIMapper<HistoryNoticeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryNoticeView> selectPage(HistoryNoticeDto dto);

}
