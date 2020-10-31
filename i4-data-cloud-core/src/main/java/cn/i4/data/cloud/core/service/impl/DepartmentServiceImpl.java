package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.DepartmentDto;
import cn.i4.data.cloud.core.entity.model.DepartmentModel;
import cn.i4.data.cloud.core.entity.view.DepartmentView;
import cn.i4.data.cloud.core.mapper.DepartmentMapper;
import cn.i4.data.cloud.core.service.IDepartmentService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-31 15:24:09
*/
@Service
@Transactional
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper,DepartmentModel> implements IDepartmentService {

	@Autowired
	DepartmentMapper mapper;

	@Override
	public IPage<DepartmentView> selectPage(DepartmentDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public DepartmentView getByUserId(Integer userId) {
		return mapper.getByUserId(userId);
	}

}
