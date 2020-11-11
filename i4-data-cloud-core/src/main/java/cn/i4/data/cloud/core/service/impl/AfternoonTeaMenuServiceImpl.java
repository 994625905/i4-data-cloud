package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
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
* @date 2020-11-10 19:54:04
*/
@Service
@Transactional
public class AfternoonTeaMenuServiceImpl extends BaseServiceImpl<AfternoonTeaMenuMapper,AfternoonTeaMenuModel> implements IAfternoonTeaMenuService {

	@Autowired
	AfternoonTeaMenuMapper mapper;

	@Override
	public IPage<AfternoonTeaMenuView> selectPage(AfternoonTeaMenuDto dto) {
    	return mapper.selectPage(dto);
    }

}
