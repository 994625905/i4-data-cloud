package cn.i4.data.cloud.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.constant.SystemConstant;
import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.result.ActionResult;
import cn.i4.data.cloud.core.entity.model.LeaveProcessCcModel;
import cn.i4.data.cloud.core.mapper.LeaveProcessCcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LeaveProcessDto;
import cn.i4.data.cloud.core.entity.model.LeaveProcessModel;
import cn.i4.data.cloud.core.entity.view.LeaveProcessView;
import cn.i4.data.cloud.core.mapper.LeaveProcessMapper;
import cn.i4.data.cloud.core.service.ILeaveProcessService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-01 14:58:27
*/
@Service
@Transactional
public class LeaveProcessServiceImpl extends BaseServiceImpl<LeaveProcessMapper,LeaveProcessModel> implements ILeaveProcessService {

	@Autowired
	LeaveProcessMapper mapper;
	@Autowired
	LeaveProcessCcMapper ccMapper;

	@Override
	public IPage<LeaveProcessView> selectPage(LeaveProcessDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Boolean apply(LeaveProcessDto dto) throws CommonException {
		LeaveProcessModel processModel = dto.getProcessModel();
		List<Integer> ccUserList = dto.getCcUserList();

		/** 录入请假申请 */
		processModel.setApplyTime(System.currentTimeMillis()/1000L);
		processModel.setUserId(dto.getUserId());
		processModel.setStatus(SystemConstant.PROCESS.STATUS.DOING);
		boolean save = this.save(processModel);
		if(!save){
			throw new CommonException("新增请假申请失败");
		}

		/** 依次录入抄送对象 */
		int insert = 0;
		for(Integer userId:ccUserList){
			LeaveProcessCcModel processCcModel = new LeaveProcessCcModel();
			processCcModel.setLeaveId(processModel.getLeaveId());
			processCcModel.setLeaveProcessId(processCcModel.getId());
			processCcModel.setUserId(userId);
			insert += ccMapper.insert(processCcModel);
		}
		if(insert <= ccUserList.size()){
			throw new CommonException("新增抄送列表失败");
		}
		return true;
	}

}
