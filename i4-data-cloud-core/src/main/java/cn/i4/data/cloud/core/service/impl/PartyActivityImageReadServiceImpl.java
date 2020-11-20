package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import java.util.Set;

import cn.i4.data.cloud.core.entity.model.PartyActivityImageLikeModel;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageReadDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageReadModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageReadView;
import cn.i4.data.cloud.core.mapper.PartyActivityImageReadMapper;
import cn.i4.data.cloud.core.service.IPartyActivityImageReadService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-18 10:11:37
*/
@Service
@Transactional
public class PartyActivityImageReadServiceImpl extends BaseServiceImpl<PartyActivityImageReadMapper,PartyActivityImageReadModel> implements IPartyActivityImageReadService {

	@Autowired
	PartyActivityImageReadMapper mapper;

	@Override
	public IPage<PartyActivityImageReadView> selectPage(PartyActivityImageReadDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Integer asyncByRedis(Set<Object> set) {
		int insert = 0;
		for(Object json:set){
			PartyActivityImageReadModel model = JSONObject.parseObject(json.toString(),PartyActivityImageReadModel.class);
			insert += this.mapper.insert(model);
		}
		return insert;
	}

}
