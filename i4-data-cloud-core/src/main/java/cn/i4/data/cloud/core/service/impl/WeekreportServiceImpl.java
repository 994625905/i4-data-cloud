package cn.i4.data.cloud.core.service.impl;

import java.util.List;
import java.util.Date;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.core.entity.model.WeekreportFileModel;
import cn.i4.data.cloud.core.mapper.WeekreportFileMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.base.service.impl.BaseServiceImpl;
import cn.i4.data.cloud.core.entity.dto.WeekreportDto;
import cn.i4.data.cloud.core.entity.model.WeekreportModel;
import cn.i4.data.cloud.core.entity.view.WeekreportView;
import cn.i4.data.cloud.core.mapper.WeekreportMapper;
import cn.i4.data.cloud.core.service.IWeekreportService;
import org.springframework.transaction.annotation.Transactional;

/**
* Service Impl
* @author wangjc
* @date 2020-11-06 09:46:07
*/
@Service
@Transactional
public class WeekreportServiceImpl extends BaseServiceImpl<WeekreportMapper,WeekreportModel> implements IWeekreportService {

	@Autowired
	WeekreportMapper mapper;
	@Autowired
	WeekreportFileMapper fileMapper;

	@Override
	public IPage<WeekreportView> selectPage(WeekreportDto dto) {
    	return mapper.selectPage(dto);
    }

	@Override
	public WeekreportView selectRealNameById(Integer id) {
		return mapper.selectRealNameById(id);
	}

	@Override
	public IPage<WeekreportView> selectAll(WeekreportDto dto) {
		return mapper.selectAll(dto);
	}

	@Override
	public Boolean insert(WeekreportDto dto) throws CommonException {
		WeekreportModel model = dto.getModel();
		List<WeekreportFileModel> fileList = dto.getFileList();

		/** 新增周报 */
		model.setMongoId(dto.getMongoId());
		model.setCreateTime(System.currentTimeMillis()/1000L);
		model.setUserId(dto.getUserId());
		boolean save = this.save(model);
		if(!save){
			throw new CommonException("新增周报失败");
		}

		/** 新增周报附件 */
		if(fileList != null && fileList.size() > 0){
			for(WeekreportFileModel fileModel:fileList){
				fileModel.setCreateTime(System.currentTimeMillis()/1000L);
				fileModel.setCreateUserId(model.getUserId());
				fileModel.setWeekreportId(model.getId());
				fileMapper.insert(fileModel);
			}
		}
		return true;
	}

	@Override
	public Boolean update(WeekreportDto dto) throws CommonException {
		WeekreportModel model = dto.getModel();
		List<WeekreportFileModel> fileList = dto.getFileList();

		/** 修改周报 */
		model.setUpdateTime(System.currentTimeMillis()/1000L);
		boolean modify = this.modify(model);
		if(!modify){
			throw new CommonException("修改周报失败");
		}

		/** 删除旧附件 */
		fileMapper.delete(new QueryWrapper<WeekreportFileModel>(){{
			eq("weekreport_id",model.getId());
		}});

		/** 新增附件 */
		if(fileList != null && fileList.size() > 0){
			for(WeekreportFileModel fileModel:fileList){
				fileModel.setCreateTime(System.currentTimeMillis()/1000L);
				fileModel.setCreateUserId(model.getUserId());
				fileModel.setWeekreportId(model.getId());
				fileMapper.insert(fileModel);
			}
		}
		return true;
	}

	@Override
	public Boolean deleteById(Integer id) throws CommonException {

		/** 删除附件 */
		fileMapper.delete(new QueryWrapper<WeekreportFileModel>(){{
			eq("weekreport_id",id);
		}});

		/** 删除周报 */
		boolean remove = this.removeById(id);
		if(!remove){
			throw new CommonException("删除失败");
		}
		return true;
	}

}
