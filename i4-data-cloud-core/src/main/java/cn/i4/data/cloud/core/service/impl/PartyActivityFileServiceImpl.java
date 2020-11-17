package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.PartyActivityFileDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityFileModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityFileView;
import cn.i4.data.cloud.core.mapper.PartyActivityFileMapper;
import cn.i4.data.cloud.core.service.IPartyActivityFileService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-14 15:39:24
*/
@Service
@Transactional
public class PartyActivityFileServiceImpl extends BaseServiceImpl<PartyActivityFileMapper,PartyActivityFileModel> implements IPartyActivityFileService {

	@Autowired
	PartyActivityFileMapper mapper;

	@Override
	public IPage<PartyActivityFileView> selectPage(PartyActivityFileDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<PartyActivityFileModel> getListByActivityId(Integer activityId) {
		List<PartyActivityFileModel> list = this.list(new QueryWrapper<PartyActivityFileModel>() {{
			eq("activity_id", activityId);
		}});
		return list;
	}

}
