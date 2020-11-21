package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeTypeDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeTypeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeTypeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public interface HistoryNoticeTypeMapper extends BaseIMapper<HistoryNoticeTypeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryNoticeTypeView> selectPage(HistoryNoticeTypeDto dto);

}
