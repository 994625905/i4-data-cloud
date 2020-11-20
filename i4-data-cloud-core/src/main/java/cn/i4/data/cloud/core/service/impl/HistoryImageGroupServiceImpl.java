package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.core.entity.model.HistoryImageModel;
import cn.i4.data.cloud.core.mapper.HistoryImageLikeMapper;
import cn.i4.data.cloud.core.mapper.HistoryImageMapper;
import cn.i4.data.cloud.core.mapper.HistoryImageReadMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.HistoryImageGroupDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageGroupModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageGroupView;
import cn.i4.data.cloud.core.mapper.HistoryImageGroupMapper;
import cn.i4.data.cloud.core.service.IHistoryImageGroupService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-19 19:47:04
*/
@Service
@Transactional
public class HistoryImageGroupServiceImpl extends BaseServiceImpl<HistoryImageGroupMapper,HistoryImageGroupModel> implements IHistoryImageGroupService {

	@Autowired
	HistoryImageGroupMapper mapper;
	@Autowired
	HistoryImageMapper imageMapper;
	@Autowired
	HistoryImageLikeMapper likeMapper;
	@Autowired
	HistoryImageReadMapper readMapper;

	@Override
	public IPage<HistoryImageGroupView> selectPage(HistoryImageGroupDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public Boolean deleteById(HistoryImageGroupDto dto) throws CommonException {

		/** 删除组下照片的点赞数 */
		likeMapper.deleteByGroupId(dto.getId());

		/** 删除组下照片的阅读数 */
		readMapper.deleteByGroupId(dto.getId());

		/** 删除组下照片 */
		imageMapper.delete(new QueryWrapper<HistoryImageModel>(){{
			eq("group_id",dto.getId());
		}});

		/** 删除组 */
		mapper.deleteById(dto.getId());

		return true;
	}

	@Override
	public HistoryImageGroupView selectByGroupId(Integer id) {
		return mapper.selectByGroupId(id);
	}

}
