package cn.i4.data.cloud.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.SystemConstantDto;
import cn.i4.data.cloud.core.entity.model.SystemConstantModel;
import cn.i4.data.cloud.core.entity.view.SystemConstantView;
import cn.i4.data.cloud.core.mapper.SystemConstantMapper;
import cn.i4.data.cloud.core.service.ISystemConstantService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-14 14:43:30
*/
@Service
@Transactional
public class SystemConstantServiceImpl extends BaseServiceImpl<SystemConstantMapper,SystemConstantModel> implements ISystemConstantService {

	@Autowired
	SystemConstantMapper mapper;

	@Override
	public IPage<SystemConstantView> selectPage(SystemConstantDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Map<String, Object> getSystemConstant() {
		List<SystemConstantModel> list = this.mapper.selectList(null);
		Map<String,Object> map = new HashMap<>();
		for(SystemConstantModel model:list){
			map.put(model.getConstantKey()+"_id",model.getId());
			map.put(model.getConstantKey(),model.getConstantValue());
			map.put(model.getConstantKey()+"_name",model.getConstantName());
		}
		return map;
	}

}
