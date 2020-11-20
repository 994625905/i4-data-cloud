package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryImageReadDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageReadModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageReadView;
import cn.i4.data.cloud.core.mapper.HistoryImageReadMapper;
import cn.i4.data.cloud.core.service.IHistoryImageReadService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-19 19:47:04
*/
@Service
@Transactional
public class HistoryImageReadServiceImpl extends BaseServiceImpl<HistoryImageReadMapper,HistoryImageReadModel> implements IHistoryImageReadService {

	@Autowired
	HistoryImageReadMapper mapper;

	@Override
	public IPage<HistoryImageReadView> selectPage(HistoryImageReadDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Integer asyncByRedis(Set<Object> readSet) {
		int insert = 0;
		for(Object json:readSet){
			HistoryImageReadModel model = JSONObject.parseObject(json.toString(),HistoryImageReadModel.class);
			insert += this.mapper.insert(model);
		}
		return insert;
	}

}
