package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.FileDto;
import cn.i4.data.cloud.core.entity.model.FileModel;
import cn.i4.data.cloud.core.entity.view.FileView;
import cn.i4.data.cloud.core.mapper.FileMapper;
import cn.i4.data.cloud.core.service.IFileService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-10-13 11:39:37
*/
@Service
@Transactional
public class FileServiceImpl extends BaseServiceImpl<FileMapper,FileModel> implements IFileService {

	@Autowired
	FileMapper mapper;

	@Override
	public IPage<FileView> selectPage(FileDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public IPage<FileView> loadImageTable(FileDto dto) {
		return mapper.loadImageTable(dto);
	}

}
