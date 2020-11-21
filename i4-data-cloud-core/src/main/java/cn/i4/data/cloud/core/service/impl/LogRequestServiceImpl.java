package cn.i4.data.cloud.core.service.impl;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LogRequestDto;
import cn.i4.data.cloud.core.entity.model.LogRequestModel;
import cn.i4.data.cloud.core.entity.view.LogRequestView;
import cn.i4.data.cloud.core.mapper.LogRequestMapper;
import cn.i4.data.cloud.core.service.ILogRequestService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-25 17:37:12
*/
@Service
@Transactional
public class LogRequestServiceImpl extends BaseServiceImpl<LogRequestMapper,LogRequestModel> implements ILogRequestService {

	@Autowired
	LogRequestMapper mapper;

	@Override
	public IPage<LogRequestView> selectPage(LogRequestDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<Map<String,Object>> selectByUserId(LogRequestDto dto) {
		List<Map<String,Object>> list = mapper.selectGroupByDate(BeanMap.create(dto));
		List<LogRequestView> data = mapper.selectByUserId(BeanMap.create(dto));

		for(Map<String,Object> map:list){
			List<Map<String,Object>> detail = new ArrayList<>();
			for(LogRequestView temp:data){
				if(temp.getDate().equals(map.get("date"))){
					Map<String,Object> t = new HashMap<>();
					t.put("time",temp.getTime());
					t.put("moduleName",temp.getModuleName());
					t.put("actionContent",temp.getActionContent());
					t.put("actionType",temp.getActionType());
					t.put("actionName",temp.getActionName());
					t.put("actionException",temp.getActionException());
					t.put("actionTime",temp.getActionTime());
					t.put("actionResult",temp.getActionResult());
					detail.add(t);
					if(detail.size() >= 10){
						break;
					}
				}
			}
			map.put("detail",detail);
			map.put("limit",detail.size()>=10?10:detail.size());
		}
		return list;
	}

	@Override
	public List<LogRequestView> selectByUserIdAndDate(LogRequestDto dto) {
		return mapper.selectByUserId(BeanMap.create(dto));
	}

	@Override
	public Integer asyncByRedis(Set<Object> logSet) {
		int insert = 0;
		for(Object json:logSet){
			insert += this.mapper.insert(JSONObject.parseObject(json.toString(),LogRequestModel.class));
		}
		return insert;
	}

}
