package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.core.entity.model.LeaveFileModel;
import cn.i4.data.cloud.core.mapper.LeaveFileMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.LeaveDto;
import cn.i4.data.cloud.core.entity.model.LeaveModel;
import cn.i4.data.cloud.core.entity.view.LeaveView;
import cn.i4.data.cloud.core.mapper.LeaveMapper;
import cn.i4.data.cloud.core.service.ILeaveService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-01 14:58:27
*/
@Service
@Transactional
public class LeaveServiceImpl extends BaseServiceImpl<LeaveMapper,LeaveModel> implements ILeaveService {

	@Autowired
	LeaveMapper mapper;
	@Autowired
	LeaveFileMapper fileMapper;

	@Override
	public IPage<LeaveView> selectPage(LeaveDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<LeaveView> selectAllLog(LeaveDto dto) {
		return mapper.selectAllLog(dto);
	}

	@Override
	public Boolean insert(LeaveDto dto) throws CommonException {
		LeaveModel model = dto.getModel();
		List<LeaveFileModel> fileList = dto.getFileList();

		/** 新增请假条 */
		model.setCreateTime(System.currentTimeMillis()/1000L);
		model.setUserId(dto.getUserId());
		boolean save = this.save(model);
		if(!save){
			throw new CommonException("新增请假条失败");
		}

		/** 新增附件 */
		if(fileList != null && fileList.size() > 0){
			for(LeaveFileModel fileModel:fileList){
				fileModel.setCreateTime(System.currentTimeMillis()/1000L);
				fileModel.setCreateUserId(dto.getUserId());
				fileModel.setLeaveId(model.getId());
				fileMapper.insert(fileModel);
			}
		}
		return true;
	}

	@Override
	public Boolean deleteByLeaveId(Integer id) throws CommonException {

		/** 删除附件 */
		fileMapper.delete(new QueryWrapper<LeaveFileModel>(){{
			eq("leave_id",id);
		}});

		/** 删除请假条 */
		boolean remove = this.removeById(id);
		if(!remove){
			throw new CommonException("删除失败");
		}
		return true;
	}

	@Override
	public Boolean update(LeaveDto dto) throws CommonException {
		LeaveModel model = dto.getModel();
		List<LeaveFileModel> fileList = dto.getFileList();

		/** 修改请假条 */
		model.setUpdateTime(System.currentTimeMillis()/1000L);
		boolean modify = this.modify(model);
		if(!modify){
			throw new CommonException("修改请假条失败");
		}

		/** 删除旧附件 */
		fileMapper.delete(new QueryWrapper<LeaveFileModel>(){{
			eq("leave_id",model.getId());
		}});

		/** 新增附件 */
		if(fileList != null && fileList.size() > 0){
			for(LeaveFileModel fileModel:fileList){
				fileModel.setCreateTime(System.currentTimeMillis()/1000L);
				fileModel.setCreateUserId(dto.getUserId());
				fileModel.setLeaveId(model.getId());
				fileMapper.insert(fileModel);
			}
		}
		return true;
	}
}
