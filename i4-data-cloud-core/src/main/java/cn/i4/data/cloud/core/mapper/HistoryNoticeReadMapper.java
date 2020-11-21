package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeReadDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeReadModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeReadView;

/**
* Mapper
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public interface HistoryNoticeReadMapper extends BaseIMapper<HistoryNoticeReadModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryNoticeReadView> selectPage(HistoryNoticeReadDto dto);

}
