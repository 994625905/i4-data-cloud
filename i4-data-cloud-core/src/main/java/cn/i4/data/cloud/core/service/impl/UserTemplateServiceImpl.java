package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.UserTemplateDto;
import cn.i4.data.cloud.core.entity.model.UserTemplateModel;
import cn.i4.data.cloud.core.entity.view.UserTemplateView;
import cn.i4.data.cloud.core.mapper.UserTemplateMapper;
import cn.i4.data.cloud.core.service.IUserTemplateService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-06 15:24:53
*/
@Service
@Transactional
public class UserTemplateServiceImpl extends BaseServiceImpl<UserTemplateMapper,UserTemplateModel> implements IUserTemplateService {

	@Autowired
	UserTemplateMapper mapper;

	@Override
	public IPage<UserTemplateView> selectPage(UserTemplateDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public UserTemplateModel selectByUserId(Integer userId) {
		UserTemplateModel templateModel = this.getOne(new QueryWrapper<UserTemplateModel>() {{
			eq("user_id", userId);
		}});
		return templateModel;
	}

}
