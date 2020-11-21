package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.WeekreportFileDto;
import cn.i4.data.cloud.core.entity.model.WeekreportFileModel;
import cn.i4.data.cloud.core.entity.view.WeekreportFileView;
import cn.i4.data.cloud.core.mapper.WeekreportFileMapper;
import cn.i4.data.cloud.core.service.IWeekreportFileService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-20 15:07:56
*/
@Service
@Transactional
public class WeekreportFileServiceImpl extends BaseServiceImpl<WeekreportFileMapper,WeekreportFileModel> implements IWeekreportFileService {

	@Autowired
	WeekreportFileMapper mapper;

	@Override
	public IPage<WeekreportFileView> selectPage(WeekreportFileDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<WeekreportFileView> selectByWeekReportId(Integer weekReportId) {
		return mapper.selectByWeekReportId(weekReportId);
	}

}
