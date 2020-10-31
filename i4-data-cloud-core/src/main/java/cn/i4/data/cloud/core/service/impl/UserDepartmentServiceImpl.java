package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.UserDepartmentDto;
import cn.i4.data.cloud.core.entity.model.UserDepartmentModel;
import cn.i4.data.cloud.core.entity.view.UserDepartmentView;
import cn.i4.data.cloud.core.mapper.UserDepartmentMapper;
import cn.i4.data.cloud.core.service.IUserDepartmentService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-31 16:11:56
*/
@Service
@Transactional
public class UserDepartmentServiceImpl extends BaseServiceImpl<UserDepartmentMapper,UserDepartmentModel> implements IUserDepartmentService {

	@Autowired
	UserDepartmentMapper mapper;

	@Override
	public IPage<UserDepartmentView> selectPage(UserDepartmentDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Boolean changeDepartment(Integer userId, Integer departmentId) throws CommonException {
		/** 先删除旧设置 */
		this.remove(new QueryWrapper<UserDepartmentModel>() {{
			eq("user_id", userId);
		}});

		/** 添加新设置 */
		UserDepartmentModel model = new UserDepartmentModel();
		model.setDepartmentId(departmentId);
		model.setUserId(userId);
		boolean save = this.save(model);
		if(!save){
			throw new CommonException("添加新设置失败");
		}
		return save;
	}

}
