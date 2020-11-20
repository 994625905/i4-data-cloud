package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryImageLikeDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageLikeModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageLikeView;
import cn.i4.data.cloud.core.mapper.HistoryImageLikeMapper;
import cn.i4.data.cloud.core.service.IHistoryImageLikeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-19 19:47:04
*/
@Service
@Transactional
public class HistoryImageLikeServiceImpl extends BaseServiceImpl<HistoryImageLikeMapper,HistoryImageLikeModel> implements IHistoryImageLikeService {

	@Autowired
	HistoryImageLikeMapper mapper;

	@Override
	public IPage<HistoryImageLikeView> selectPage(HistoryImageLikeDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Integer asyncByRedis(Set<Object> likeSet) {
		int insert = 0;
		for(Object json:likeSet){
			HistoryImageLikeModel model = JSONObject.parseObject(json.toString(),HistoryImageLikeModel.class);
			insert += this.mapper.insert(model);
		}
		return insert;
	}

}
