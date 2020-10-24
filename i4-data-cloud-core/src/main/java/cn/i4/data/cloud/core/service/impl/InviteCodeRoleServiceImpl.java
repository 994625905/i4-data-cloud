package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.InviteCodeRoleDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeRoleModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeRoleView;
import cn.i4.data.cloud.core.mapper.InviteCodeRoleMapper;
import cn.i4.data.cloud.core.service.IInviteCodeRoleService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-18 14:34:30
*/
@Service
@Transactional
public class InviteCodeRoleServiceImpl extends BaseServiceImpl<InviteCodeRoleMapper,InviteCodeRoleModel> implements IInviteCodeRoleService {

	@Autowired
	InviteCodeRoleMapper mapper;

	@Override
	public IPage<InviteCodeRoleView> selectPage(InviteCodeRoleDto dto) {
    	return mapper.selectPage(dto);
    }

}
