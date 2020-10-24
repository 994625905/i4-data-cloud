package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.RoleDto;
import cn.i4.data.cloud.core.entity.model.RoleModel;
import cn.i4.data.cloud.core.entity.view.RoleView;
import cn.i4.data.cloud.core.mapper.RoleMapper;
import cn.i4.data.cloud.core.service.IRoleService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-11 13:42:25
*/
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper,RoleModel> implements IRoleService {

	@Autowired
	RoleMapper mapper;

	@Override
	public IPage<RoleView> selectPage(RoleDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<RoleView> selectRolesByUserId(Integer userId) {
		return mapper.selectRolesByUserId(userId);
	}

	@Override
	public List<RoleModel> selectRolesByIds(List<Integer> roleIds) {
		List<RoleModel> list = this.baseMapper.selectList(new QueryWrapper<RoleModel>() {{
			in("id", roleIds);
		}});
		return list;
	}

}
