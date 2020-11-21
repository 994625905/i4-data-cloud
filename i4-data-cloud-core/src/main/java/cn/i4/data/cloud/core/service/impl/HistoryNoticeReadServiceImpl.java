package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeReadDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeReadModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeReadView;
import cn.i4.data.cloud.core.mapper.HistoryNoticeReadMapper;
import cn.i4.data.cloud.core.service.IHistoryNoticeReadService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-21 14:11:04
*/
@Service
@Transactional
public class HistoryNoticeReadServiceImpl extends BaseServiceImpl<HistoryNoticeReadMapper,HistoryNoticeReadModel> implements IHistoryNoticeReadService {

	@Autowired
	HistoryNoticeReadMapper mapper;

	@Override
	public IPage<HistoryNoticeReadView> selectPage(HistoryNoticeReadDto dto) {
    	return mapper.selectPage(dto);
    }

}
