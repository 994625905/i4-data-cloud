package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.RichTextDto;
import cn.i4.data.cloud.core.entity.model.RichTextModel;
import cn.i4.data.cloud.core.entity.view.RichTextView;

/**
* Mapper
* @author wangjc
* @date 2020-11-04 15:55:45
*/
public interface RichTextMapper extends BaseIMapper<RichTextModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<RichTextView> selectPage(RichTextDto dto);

}
