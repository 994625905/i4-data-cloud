package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.core.entity.model.PartyActivityFileModel;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageModel;
import cn.i4.data.cloud.core.entity.model.PartyActivitySignModel;
import cn.i4.data.cloud.core.mapper.PartyActivityFileMapper;
import cn.i4.data.cloud.core.mapper.PartyActivityImageMapper;
import cn.i4.data.cloud.core.mapper.PartyActivitySignMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.PartyActivityDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityView;
import cn.i4.data.cloud.core.mapper.PartyActivityMapper;
import cn.i4.data.cloud.core.service.IPartyActivityService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-14 14:20:33
*/
@Service
@Transactional
public class PartyActivityServiceImpl extends BaseServiceImpl<PartyActivityMapper,PartyActivityModel> implements IPartyActivityService {

	@Autowired
	PartyActivityMapper mapper;
	@Autowired
	private PartyActivityFileMapper fileMapper;
	@Autowired
	private PartyActivityImageMapper imageMapper;
	@Autowired
	private PartyActivitySignMapper signMapper;

	@Override
	public IPage<PartyActivityView> selectPage(PartyActivityDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<PartyActivityView> selectActivity(PartyActivityDto dto) {
		return mapper.selectActivity(dto);
	}

	@Override
	public Boolean deleteById(Integer id) throws CommonException {

		/** 先删除附件 */
		fileMapper.delete(new QueryWrapper<PartyActivityFileModel>(){{
			eq("activity_id",id);
		}});

		/** 再删除照片墙 */
		imageMapper.delete(new QueryWrapper<PartyActivityImageModel>(){{
			eq("activity_id",id);
		}});

		/** 然后删除签到表 */
		signMapper.delete(new QueryWrapper<PartyActivitySignModel>(){{
			eq("activity_id",id);
		}});

		/** 最后删除活动 */
		mapper.deleteById(id);

		return true;
	}

	@Override
	public Boolean insert(PartyActivityDto dto) throws CommonException {
		PartyActivityModel model = dto.getModel();
		List<PartyActivityFileModel> fileList = dto.getFileList();

		/** 新增活动 */
		model.setCreateTime(System.currentTimeMillis()/1000L);
		model.setCreateUserId(dto.getUserId());
		model.setMongoId(dto.getMongoId());
		boolean save = this.save(model);
		if(!save){
			throw new CommonException("新增活动失败");
		}

		/** 新增附件 */
		if(fileList != null && fileList.size() > 0){
			for(PartyActivityFileModel file:fileList){
				file.setActivityId(model.getId());
				file.setCreateUserId(dto.getUserId());
				file.setCreateTime(System.currentTimeMillis()/1000L);
				fileMapper.insert(file);
			}
		}
		return true;
	}

	@Override
	public Boolean update(PartyActivityDto dto) throws CommonException {
		PartyActivityModel model = dto.getModel();
		List<PartyActivityFileModel> fileList = dto.getFileList();

		/** 删除之前所有的附件 */
		fileMapper.delete(new QueryWrapper<PartyActivityFileModel>(){{
			eq("activity_id",model.getId());
		}});

		/** 修改活动 */
		model.setUpdateTime(System.currentTimeMillis()/1000L);
		boolean modify = this.modify(model);
		if(!modify){
			throw new CommonException("修改活动失败");
		}

		/** 新增附件 */
		if(fileList != null && fileList.size() > 0){
			for(PartyActivityFileModel file:fileList){
				file.setActivityId(model.getId());
				file.setCreateUserId(dto.getUserId());
				file.setCreateTime(System.currentTimeMillis()/1000L);
				fileMapper.insert(file);
			}
		}
		return true;
	}

}
