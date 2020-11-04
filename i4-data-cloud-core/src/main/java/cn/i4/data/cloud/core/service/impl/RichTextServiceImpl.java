package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.RichTextDto;
import cn.i4.data.cloud.core.entity.model.RichTextModel;
import cn.i4.data.cloud.core.entity.view.RichTextView;
import cn.i4.data.cloud.core.mapper.RichTextMapper;
import cn.i4.data.cloud.core.service.IRichTextService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-04 15:55:45
*/
@Service
@Transactional
public class RichTextServiceImpl extends BaseServiceImpl<RichTextMapper,RichTextModel> implements IRichTextService {

	@Autowired
	RichTextMapper mapper;

	@Override
	public IPage<RichTextView> selectPage(RichTextDto dto) {
    	return mapper.selectPage(dto);
    }

}
