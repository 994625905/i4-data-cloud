package cn.i4.data.cloud.core.service.impl;

import cn.i4.data.cloud.core.entity.dto.UserDto;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.entity.view.UserView;
import cn.i4.data.cloud.core.mapper.UserMapper;
import cn.i4.data.cloud.core.service.IUserService;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Service Impl
* @author wangjc
* @date 2020-10-10 10:14:11
*/
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserModel> implements IUserService {

	@Autowired
	UserMapper mapper;

	@Override
	public IPage<UserView> selectPage(UserDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public UserModel login(String loginName, String password) {
		return mapper.login(loginName,password);
	}

	@Override
	public UserView selectById(Integer userId) {
		return mapper.selectUserViewById(userId);
	}

	@Override
	public List<UserModel> selectListNotUserId(Integer userId) {
		List<UserModel> list = this.list(new QueryWrapper<UserModel>() {{
			ne("id", userId);
		}});
		return list;
	}

}
