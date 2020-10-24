package cn.i4.data.cloud.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.MenuButtonDto;
import cn.i4.data.cloud.core.entity.model.MenuButtonModel;
import cn.i4.data.cloud.core.entity.view.MenuButtonView;
import cn.i4.data.cloud.core.mapper.MenuButtonMapper;
import cn.i4.data.cloud.core.service.IMenuButtonService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-11 13:42:25
*/
@Service
@Transactional
public class MenuButtonServiceImpl extends BaseServiceImpl<MenuButtonMapper,MenuButtonModel> implements IMenuButtonService {

	@Autowired
	MenuButtonMapper mapper;

	@Override
	public IPage<MenuButtonView> selectPage(MenuButtonDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<MenuButtonView> getMenuButtonTreeByUserId(Integer userId) {
		List<MenuButtonView> list = mapper.getMenuButtonTreeByUserId(userId, SystemConstant.MENU.ACTIVE);
		return this.getMenu(list,0);
	}

	@Override
	public List<MenuButtonView> getMenuButtonTree() {
		List<MenuButtonView> list = mapper.getMenuButtonTree(SystemConstant.MENU.ACTIVE);
		return this.getMenu(list,0);
	}

	@Override
	public Integer getMaxSortByPid(Integer pid) {
		return mapper.getMaxSortByPid(pid);
	}

	@Override
	public List<MenuButtonView> getMenuButtonsByPid(Integer pid) {
		return mapper.getMenuButtonsByPid(pid);
	}

	@Override
	public List<MenuButtonView> getMenuButtonByRoleId(Integer roleId) {
		return mapper.getMenuButtonByRoleId(roleId,SystemConstant.MENU.ACTIVE);
	}

	/**
	 * 获取菜单，递归方式生成树节点
	 * @param list
	 * @param parentId
	 * @return
	 */
	private List<MenuButtonView> getMenu(List<MenuButtonView> list,Integer parentId){
		List<MenuButtonView> res = new ArrayList<>();
		for(MenuButtonView menuView:list){
			if(menuView.getParentId().equals(parentId)){
				MenuButtonView menu = menuView;
				menu.setChild(getMenu(list,menu.getId()));
				res.add(menu);
			}
		}
		return res;
	}

}
