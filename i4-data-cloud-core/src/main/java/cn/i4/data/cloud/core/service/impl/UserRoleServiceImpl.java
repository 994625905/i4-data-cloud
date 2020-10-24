package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.core.entity.model.InviteCodeRoleModel;
import cn.i4.data.cloud.core.mapper.InviteCodeRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.UserRoleDto;
import cn.i4.data.cloud.core.entity.model.UserRoleModel;
import cn.i4.data.cloud.core.entity.view.UserRoleView;
import cn.i4.data.cloud.core.mapper.UserRoleMapper;
import cn.i4.data.cloud.core.service.IUserRoleService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-11 13:42:25
*/
@Service
@Transactional
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper,UserRoleModel> implements IUserRoleService {

	@Autowired
	UserRoleMapper mapper;
	@Autowired
	InviteCodeRoleMapper inviteCodeRoleMapper;

	@Override
	public IPage<UserRoleView> selectPage(UserRoleDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Boolean setUserRoleByInviteCodeId(Integer userId, Integer inviteCodeId) {
		List<InviteCodeRoleModel> roleList = inviteCodeRoleMapper.selectList(new QueryWrapper<InviteCodeRoleModel>() {{
			eq("invite_code_id", inviteCodeId);
		}});
		Boolean save = true;
		for(InviteCodeRoleModel inviteCodeRoleModel:roleList){
			UserRoleModel model = new UserRoleModel();
			model.setRoleId(inviteCodeRoleModel.getRoleId());
			model.setUserId(userId);
			save = this.save(model) && save;
		}
		return save;
	}

}
