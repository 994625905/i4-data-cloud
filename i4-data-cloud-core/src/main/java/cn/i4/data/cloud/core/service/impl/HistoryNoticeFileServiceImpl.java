package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeFileDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeFileModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeFileView;
import cn.i4.data.cloud.core.mapper.HistoryNoticeFileMapper;
import cn.i4.data.cloud.core.service.IHistoryNoticeFileService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-21 14:11:04
*/
@Service
@Transactional
public class HistoryNoticeFileServiceImpl extends BaseServiceImpl<HistoryNoticeFileMapper,HistoryNoticeFileModel> implements IHistoryNoticeFileService {

	@Autowired
	HistoryNoticeFileMapper mapper;

	@Override
	public IPage<HistoryNoticeFileView> selectPage(HistoryNoticeFileDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<HistoryNoticeFileView> selectByNoticeId(Integer noticeId) {
		return mapper.selectByNoticeId(noticeId);
	}

}
