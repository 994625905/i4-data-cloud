package cn.i4.data.cloud.core.service.impl;

import cn.i4.data.cloud.core.entity.dto.UserInfoDto;
import cn.i4.data.cloud.core.entity.model.UserInfoModel;
import cn.i4.data.cloud.core.entity.view.UserInfoView;
import cn.i4.data.cloud.core.mapper.UserInfoMapper;
import cn.i4.data.cloud.core.service.IUserInfoService;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-10 10:14:12
*/
@Service
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfoModel> implements IUserInfoService {

	@Autowired
	UserInfoMapper mapper;

	@Override
	public IPage<UserInfoView> selectPage(UserInfoDto dto) {
    	return mapper.selectPage(dto);
    }

}
