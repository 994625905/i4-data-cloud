package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.core.entity.view.DepartmentView;
import cn.i4.data.cloud.core.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.InviteCodeDepartmentDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeDepartmentModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeDepartmentView;
import cn.i4.data.cloud.core.mapper.InviteCodeDepartmentMapper;
import cn.i4.data.cloud.core.service.IInviteCodeDepartmentService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-31 17:10:53
*/
@Service
@Transactional
public class InviteCodeDepartmentServiceImpl extends BaseServiceImpl<InviteCodeDepartmentMapper,InviteCodeDepartmentModel> implements IInviteCodeDepartmentService {

	@Autowired
	InviteCodeDepartmentMapper mapper;
	@Autowired
	DepartmentMapper departmentMapper;

	@Override
	public IPage<InviteCodeDepartmentView> selectPage(InviteCodeDepartmentDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public DepartmentView selectDeploymentByid(Integer departmentId) {
		return new DepartmentView(departmentMapper.selectById(departmentId));
	}

}
