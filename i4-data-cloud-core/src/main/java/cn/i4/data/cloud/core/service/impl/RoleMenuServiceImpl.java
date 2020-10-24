package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.RoleMenuDto;
import cn.i4.data.cloud.core.entity.model.RoleMenuModel;
import cn.i4.data.cloud.core.entity.view.RoleMenuView;
import cn.i4.data.cloud.core.mapper.RoleMenuMapper;
import cn.i4.data.cloud.core.service.IRoleMenuService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-11 13:42:25
*/
@Service
@Transactional
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuMapper,RoleMenuModel> implements IRoleMenuService {

	@Autowired
	RoleMenuMapper mapper;

	@Override
	public IPage<RoleMenuView> selectPage(RoleMenuDto dto) {
    	return mapper.selectPage(dto);
    }

}
