package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeView;
import cn.i4.data.cloud.core.mapper.HistoryNoticeMapper;
import cn.i4.data.cloud.core.service.IHistoryNoticeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-21 14:11:04
*/
@Service
@Transactional
public class HistoryNoticeServiceImpl extends BaseServiceImpl<HistoryNoticeMapper,HistoryNoticeModel> implements IHistoryNoticeService {

	@Autowired
	HistoryNoticeMapper mapper;

	@Override
	public IPage<HistoryNoticeView> selectPage(HistoryNoticeDto dto) {
    	return mapper.selectPage(dto);
    }

}
