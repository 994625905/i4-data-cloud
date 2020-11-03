package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessCcDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessCcModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessCcView;
import cn.i4.data.cloud.core.mapper.LeaveProcessCcMapper;
import cn.i4.data.cloud.core.service.ILeaveProcessCcService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-03 18:13:38
*/
@Service
@Transactional
public class LeaveProcessCcServiceImpl extends BaseServiceImpl<LeaveProcessCcMapper,LeaveProcessCcModel> implements ILeaveProcessCcService {

	@Autowired
	LeaveProcessCcMapper mapper;

	@Override
	public IPage<LeaveProcessCcView> selectPage(LeaveProcessCcDto dto) {
    	return mapper.selectPage(dto);
    }

}
