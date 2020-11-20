package cn.i4.data.cloud.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.core.entity.model.HistoryImageLikeModel;
import cn.i4.data.cloud.core.entity.model.HistoryImageReadModel;
import cn.i4.data.cloud.core.entity.view.FileView;
import cn.i4.data.cloud.core.mapper.HistoryImageLikeMapper;
import cn.i4.data.cloud.core.mapper.HistoryImageReadMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryImageDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageView;
import cn.i4.data.cloud.core.mapper.HistoryImageMapper;
import cn.i4.data.cloud.core.service.IHistoryImageService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-19 19:47:04
*/
@Service
@Transactional
public class HistoryImageServiceImpl extends BaseServiceImpl<HistoryImageMapper,HistoryImageModel> implements IHistoryImageService {

	@Autowired
	HistoryImageMapper mapper;
	@Autowired
	HistoryImageReadMapper readMapper;
	@Autowired
	HistoryImageLikeMapper likeMapper;

	@Override
	public IPage<HistoryImageView> selectPage(HistoryImageDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<HistoryImageModel> selectByGroupId(Integer groupId) {
		List<HistoryImageModel> list = this.list(new QueryWrapper<HistoryImageModel>() {{
			eq("group_id", groupId);
		}});
		return list;
	}

	@Override
	public List<HistoryImageView> uploadImage(HistoryImageDto dto) throws CommonException {
		List<HistoryImageView> list = new ArrayList<>();
		Boolean flag = true;

		for(FileView file:dto.getImageList()){

			HistoryImageModel model = new HistoryImageModel();
			model.setGroupId(dto.getGroupId());
			model.setCreateTime(System.currentTimeMillis()/1000L);
			model.setCreateUserId(dto.getUserId());
			model.setHeight(file.getHeight());
			model.setWidth(file.getWidth());
			model.setName(file.getName());
			model.setSize(file.getSize());
			model.setSuffix(file.getSuffix());
			model.setUrl(file.getUrl());
			flag  = this.save(model) && flag;

			list.add(new HistoryImageView(model));
		}
		if(!flag){
			throw new CommonException("上传中间失败");
		}
		return list;
	}

	@Override
	public Boolean deleteById(HistoryImageDto dto) throws CommonException {
		/** 删除点赞 */
		likeMapper.delete(new QueryWrapper<HistoryImageLikeModel>(){{
			eq("image_id",dto.getId());
		}});
		/** 删除阅读 */
		readMapper.delete(new QueryWrapper<HistoryImageReadModel>(){{
			eq("image_id",dto.getId());
		}});
		mapper.deleteById(dto.getId());
		return true;
	}


}
