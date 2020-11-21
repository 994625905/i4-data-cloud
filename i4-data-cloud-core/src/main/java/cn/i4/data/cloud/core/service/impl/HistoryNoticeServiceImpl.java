package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.util.RichTextUtil;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeFileModel;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeReadModel;
import cn.i4.data.cloud.core.mapper.HistoryNoticeFileMapper;
import cn.i4.data.cloud.core.mapper.HistoryNoticeReadMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeView;
import cn.i4.data.cloud.core.mapper.HistoryNoticeMapper;
import cn.i4.data.cloud.core.service.IHistoryNoticeService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-21 14:11:04
*/
@Service
@Transactional
public class HistoryNoticeServiceImpl extends BaseServiceImpl<HistoryNoticeMapper,HistoryNoticeModel> implements IHistoryNoticeService {

	@Autowired
	HistoryNoticeMapper mapper;
	@Autowired
	HistoryNoticeFileMapper fileMapper;
	@Autowired
	HistoryNoticeReadMapper readMapper;

	@Override
	public IPage<HistoryNoticeView> selectPage(HistoryNoticeDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Boolean deleteById(Integer id) throws CommonException {

		/** 删除附件 */
		fileMapper.delete(new QueryWrapper<HistoryNoticeFileModel>(){{
			eq("notice_id",id);
		}});

		/** 删除阅读 */
		readMapper.delete(new QueryWrapper<HistoryNoticeReadModel>(){{
			eq("notice_id",id);
		}});

		/** 删除附件 */
		boolean remove = this.removeById(id);
		if(!remove){
			throw new CommonException("删除附件失败");
		}
		return true;
	}

	@Override
	public Boolean insert(HistoryNoticeDto dto) throws CommonException {
		HistoryNoticeModel model = dto.getModel();
		List<HistoryNoticeFileModel> fileList = dto.getFileList();

		/** 新增公告 */
		model.setCreateUserId(dto.getUserId());
		model.setCreateTime(System.currentTimeMillis()/1000L);
		model.setMongoId(dto.getMongoId());
		model.setExplainInfo(RichTextUtil.getExplain(dto.getContent()));
		boolean save = this.save(model);
		if(!save){
			throw new CommonException("新增公告失败");
		}

		/** 新增附件 */
		if(fileList != null && fileList.size()>0){
			for(HistoryNoticeFileModel fileModel:fileList){
				fileModel.setCreateTime(System.currentTimeMillis()/1000L);
				fileModel.setCreateUserId(dto.getUserId());
				fileModel.setNoticeId(model.getId());
				fileMapper.insert(fileModel);
			}
		}
		return true;
	}

	@Override
	public Boolean update(HistoryNoticeDto dto) throws CommonException {
		HistoryNoticeModel model = dto.getModel();
		List<HistoryNoticeFileModel> fileList = dto.getFileList();

		/** 修改公告 */
		model.setUpdateTime(System.currentTimeMillis()/1000L);
		model.setExplainInfo(RichTextUtil.getExplain(dto.getContent()));
		boolean modify = this.modify(model);
		if(!modify){
			throw new CommonException("修改公告失败");
		}

		/** 删除附件 */
		fileMapper.delete(new QueryWrapper<HistoryNoticeFileModel>(){{
			eq("notice_id",model.getId());
		}});

		/** 新增附件 */
		if(fileList != null && fileList.size()>0){
			for(HistoryNoticeFileModel fileModel:fileList){
				fileModel.setCreateTime(System.currentTimeMillis()/1000L);
				fileModel.setCreateUserId(dto.getUserId());
				fileModel.setNoticeId(model.getId());
				fileMapper.insert(fileModel);
			}
		}
		return true;
	}

	@Override
	public HistoryNoticeView selectById(Integer id) {
		return mapper.selectByNoticeId(id);
	}

}
