package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeTypeDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeTypeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeTypeView;
import cn.i4.data.cloud.core.mapper.HistoryNoticeTypeMapper;
import cn.i4.data.cloud.core.service.IHistoryNoticeTypeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-21 14:11:04
*/
@Service
@Transactional
public class HistoryNoticeTypeServiceImpl extends BaseServiceImpl<HistoryNoticeTypeMapper,HistoryNoticeTypeModel> implements IHistoryNoticeTypeService {

	@Autowired
	HistoryNoticeTypeMapper mapper;

	@Override
	public IPage<HistoryNoticeTypeView> selectPage(HistoryNoticeTypeDto dto) {
    	return mapper.selectPage(dto);
    }

}
