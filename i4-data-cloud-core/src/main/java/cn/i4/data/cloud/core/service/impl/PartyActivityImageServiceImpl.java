package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageView;
import cn.i4.data.cloud.core.mapper.PartyActivityImageMapper;
import cn.i4.data.cloud.core.service.IPartyActivityImageService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-14 14:20:33
*/
@Service
@Transactional
public class PartyActivityImageServiceImpl extends BaseServiceImpl<PartyActivityImageMapper,PartyActivityImageModel> implements IPartyActivityImageService {

	@Autowired
	PartyActivityImageMapper mapper;

	@Override
	public IPage<PartyActivityImageView> selectPage(PartyActivityImageDto dto) {
    	return mapper.selectPage(dto);
    }

}
