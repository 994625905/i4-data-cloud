package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.RichTextDto;
import cn.i4.data.cloud.core.entity.model.RichTextModel;
import cn.i4.data.cloud.core.entity.view.RichTextView;

/**
* Service
* @author wangjc
* @date 2020-11-04 15:55:45
*/
public interface IRichTextService extends BaseService<RichTextModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<RichTextView> selectPage(RichTextDto dto);

}
