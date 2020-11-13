package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaSelectDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaSelectModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;
import cn.i4.data.cloud.core.mapper.AfternoonTeaSelectMapper;
import cn.i4.data.cloud.core.service.IAfternoonTeaSelectService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-10 19:54:04
*/
@Service
@Transactional
public class AfternoonTeaSelectServiceImpl extends BaseServiceImpl<AfternoonTeaSelectMapper,AfternoonTeaSelectModel> implements IAfternoonTeaSelectService {

	@Autowired
	AfternoonTeaSelectMapper mapper;

	@Override
	public IPage<AfternoonTeaSelectView> selectPage(AfternoonTeaSelectDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Boolean order(AfternoonTeaSelectDto dto) throws CommonException {
		List<AfternoonTeaSelectModel> selectList = dto.getSelectList();

		/** 先清空之前的 */
		this.mapper.delete(new QueryWrapper<AfternoonTeaSelectModel>(){{
			eq("tea_task_id",selectList.get(0).getTeaTaskId());
		}});

		/** 再做新增 */
		int insert = 0;
		for(AfternoonTeaSelectModel model:selectList){
			model.setCreateTime(System.currentTimeMillis()/1000L);
			model.setSelectUserId(dto.getUserId());
			insert += this.mapper.insert(model);
		}
		if(insert != selectList.size()){
			throw new CommonException("点单失败,新增数据异常");
		}
		return true;
	}

	@Override
	public List<AfternoonTeaSelectModel> getListByTaskId(Integer taskId) {
		List<AfternoonTeaSelectModel> list = this.list(new QueryWrapper<AfternoonTeaSelectModel>() {{
			eq("tea_task_id", taskId);
		}});
		return list;
	}

	@Override
	public IPage<AfternoonTeaSelectView> selectDetailTable(AfternoonTeaSelectDto dto) {
		return mapper.selectDetailTable(dto);
	}

}
