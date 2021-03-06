package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.InviteCodeDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeView;
import cn.i4.data.cloud.core.mapper.InviteCodeMapper;
import cn.i4.data.cloud.core.service.IInviteCodeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-18 14:34:31
*/
@Service
@Transactional
public class InviteCodeServiceImpl extends BaseServiceImpl<InviteCodeMapper,InviteCodeModel> implements IInviteCodeService {

	@Autowired
	InviteCodeMapper mapper;

	@Override
	public IPage<InviteCodeView> selectPage(InviteCodeDto dto) {
    	return mapper.selectPage(dto);
    }

}
