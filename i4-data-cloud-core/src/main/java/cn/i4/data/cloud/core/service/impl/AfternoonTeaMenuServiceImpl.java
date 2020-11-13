package cn.i4.data.cloud.core.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.core.entity.view.AfternoonTeaView;
import cn.i4.data.cloud.core.mapper.AfternoonTeaMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaMenuDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaMenuView;
import cn.i4.data.cloud.core.mapper.AfternoonTeaMenuMapper;
import cn.i4.data.cloud.core.service.IAfternoonTeaMenuService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-11 11:41:50
*/
@Service
@Transactional
public class AfternoonTeaMenuServiceImpl extends BaseServiceImpl<AfternoonTeaMenuMapper,AfternoonTeaMenuModel> implements IAfternoonTeaMenuService {

	@Autowired
	AfternoonTeaMenuMapper mapper;
	@Autowired
	AfternoonTeaMapper teaMapper;

	@Override
	public IPage<AfternoonTeaMenuView> selectPage(AfternoonTeaMenuDto dto) {
    	return mapper.selectPage(dto);
    }

	/**
	 * 此处一个方法多用，
	 * @param taskId
	 * @return
	 */
	@Override
	public List<AfternoonTeaMenuView> getListByTaskId(Integer taskId) {
		List<AfternoonTeaMenuModel> menuList = this.list(new QueryWrapper<AfternoonTeaMenuModel>() {{
			eq("tea_task_id", taskId);
		}});
		List<AfternoonTeaMenuView> list = new ArrayList<>();

		for(AfternoonTeaMenuModel model : menuList){
			AfternoonTeaMenuView view = new AfternoonTeaMenuView(model);

			List<AfternoonTeaView> teaList = teaMapper.selectByIds(Arrays.asList(model.getTeaIds().split(",")),view.getId(),taskId);



			view.setTeaList(teaList);
			list.add(view);
		}
		return list;
	}

}
