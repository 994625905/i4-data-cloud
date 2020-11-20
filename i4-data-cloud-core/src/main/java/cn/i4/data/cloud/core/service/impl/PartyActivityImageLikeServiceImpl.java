package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageLikeDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageLikeModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageLikeView;
import cn.i4.data.cloud.core.mapper.PartyActivityImageLikeMapper;
import cn.i4.data.cloud.core.service.IPartyActivityImageLikeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-18 10:11:37
*/
@Service
@Transactional
public class PartyActivityImageLikeServiceImpl extends BaseServiceImpl<PartyActivityImageLikeMapper,PartyActivityImageLikeModel> implements IPartyActivityImageLikeService {

	@Autowired
	PartyActivityImageLikeMapper mapper;

	@Override
	public IPage<PartyActivityImageLikeView> selectPage(PartyActivityImageLikeDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Integer asyncByRedis(Set<Object> set) {
		int insert = 0;
		for(Object json:set){
			PartyActivityImageLikeModel model = JSONObject.parseObject(json.toString(),PartyActivityImageLikeModel.class);
			insert += this.mapper.insert(model);
		}
		return insert;
	}

}
