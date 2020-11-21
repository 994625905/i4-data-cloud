package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeFileDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeFileModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeFileView;

/**
* Mapper
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public interface HistoryNoticeFileMapper extends BaseIMapper<HistoryNoticeFileModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryNoticeFileView> selectPage(HistoryNoticeFileDto dto);

}
