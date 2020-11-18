package cn.i4.data.cloud.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageLikeModel;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageReadModel;
import cn.i4.data.cloud.core.entity.view.FileView;
import cn.i4.data.cloud.core.mapper.PartyActivityImageLikeMapper;
import cn.i4.data.cloud.core.mapper.PartyActivityImageReadMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageView;
import cn.i4.data.cloud.core.mapper.PartyActivityImageMapper;
import cn.i4.data.cloud.core.service.IPartyActivityImageService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-14 14:20:33
*/
@Service
@Transactional
public class PartyActivityImageServiceImpl extends BaseServiceImpl<PartyActivityImageMapper,PartyActivityImageModel> implements IPartyActivityImageService {

	@Autowired
	PartyActivityImageMapper mapper;
	@Autowired
	PartyActivityImageReadMapper readMapper;
	@Autowired
	PartyActivityImageLikeMapper likeMapper;

	@Override
	public IPage<PartyActivityImageView> selectPage(PartyActivityImageDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public List<PartyActivityImageView> uploadImage(PartyActivityImageDto dto) throws CommonException {
		List<PartyActivityImageView> list = new ArrayList<>();
		Boolean flag = true;

		for(FileView file:dto.getImageList()){

			PartyActivityImageModel model = new PartyActivityImageModel();
			model.setActivityId(dto.getActivityId());
			model.setCreateTime(System.currentTimeMillis()/1000L);
			model.setCreateUserId(dto.getUserId());
			model.setHeight(file.getHeight());
			model.setWidth(file.getWidth());
			model.setName(file.getName());
			model.setSize(file.getSize());
			model.setSuffix(file.getSuffix());
			model.setUrl(file.getUrl());
			flag  = this.save(model) && flag;

			list.add(new PartyActivityImageView(model));
		}
		if(!flag){
			throw new CommonException("上传中间失败");
		}
		return list;
	}

	@Override
	public Boolean deleteById(PartyActivityImageDto dto) throws CommonException {

		/** 删除点赞 */
		likeMapper.delete(new QueryWrapper<PartyActivityImageLikeModel>(){{
			eq("image_id",dto.getId());
		}});
		/** 删除阅读 */
		readMapper.delete(new QueryWrapper<PartyActivityImageReadModel>(){{
			eq("image_id",dto.getId());
		}});
		mapper.deleteById(dto.getId());
		return true;
	}

}
