package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import cn.i4.data.cloud.core.mapper.AfternoonTeaMenuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;
import cn.i4.data.cloud.core.mapper.AfternoonTeaTaskMapper;
import cn.i4.data.cloud.core.service.IAfternoonTeaTaskService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-10 19:54:04
*/
@Service
@Transactional
public class AfternoonTeaTaskServiceImpl extends BaseServiceImpl<AfternoonTeaTaskMapper,AfternoonTeaTaskModel> implements IAfternoonTeaTaskService {

	@Autowired
	AfternoonTeaTaskMapper mapper;
	@Autowired
	AfternoonTeaMenuMapper menuMapper;

	@Override
	public IPage<AfternoonTeaTaskView> selectPage(AfternoonTeaTaskDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<AfternoonTeaTaskView> selectTaskDeploy(AfternoonTeaTaskDto dto) {
		return mapper.selectTaskDeploy(dto);
	}

	@Override
	public Boolean deleteById(Integer id) throws CommonException {
		/** 删除menu */
		menuMapper.delete(new QueryWrapper<AfternoonTeaMenuModel>() {{
			eq("tea_task_id", id);
		}});
		/** 删除task */
		boolean remove = this.removeById(id);
		if(!remove){
			throw new CommonException("删除task失败");
		}
		return true;
	}

	@Override
	public Boolean insert(AfternoonTeaTaskDto dto) throws CommonException {
		AfternoonTeaTaskModel model = dto.getModel();
		List<AfternoonTeaMenuModel> menuList = dto.getMenuList();

		/** 新增task */
		model.setCreateUserId(dto.getUserId());
		model.setCreateTime(System.currentTimeMillis()/1000L);
		boolean save = this.save(model);
		if(!save){
			throw new CommonException("新增task失败");
		}

		/** 新增menu */
		int insert = 0;
		for(AfternoonTeaMenuModel menuModel:menuList){
			menuModel.setCreateTime(System.currentTimeMillis()/1000L);
			menuModel.setCreateUserId(dto.getUserId());
			menuModel.setTeaTaskId(model.getId());
			insert += menuMapper.insert(menuModel);
		}
		if(insert != menuList.size()){
			throw new CommonException("新增menu失败");
		}
		return true;
	}

	@Override
	public Boolean update(AfternoonTeaTaskDto dto) throws CommonException {
		AfternoonTeaTaskModel model = dto.getModel();
		List<AfternoonTeaMenuModel> menuList = dto.getMenuList();

		/** 删除menu */
		menuMapper.delete(new QueryWrapper<AfternoonTeaMenuModel>() {{
			eq("tea_task_id", model.getId());
		}});

		/** 修改task */
		model.setUpdateTime(System.currentTimeMillis()/1000L);
		boolean modify = this.modify(model);
		if(!modify){
			throw new CommonException("修改task失败");
		}

		/** 新增menu */
		int insert = 0;
		for(AfternoonTeaMenuModel menuModel:menuList){
			menuModel.setCreateTime(System.currentTimeMillis()/1000L);
			menuModel.setCreateUserId(dto.getUserId());
			menuModel.setTeaTaskId(model.getId());
			insert += menuMapper.insert(menuModel);
		}
		if(insert != menuList.size()){
			throw new CommonException("新增menu失败");
		}
		return true;
	}

}
