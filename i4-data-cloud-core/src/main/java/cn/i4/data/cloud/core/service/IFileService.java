package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.FileDto;
import cn.i4.data.cloud.core.entity.model.FileModel;
import cn.i4.data.cloud.core.entity.view.FileView;

/**
* Service
* @author wangjc
* @date 2020-10-13 11:39:37
*/
public interface IFileService extends BaseService<FileModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<FileView> selectPage(FileDto dto);

    /**
     * 根据条件查询图片
     * @param dto
     * @return
     */
    IPage<FileView> loadImageTable(FileDto dto);
}
