package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.FileDto;
import cn.i4.data.cloud.core.entity.model.FileModel;
import cn.i4.data.cloud.core.entity.view.FileView;

/**
* Mapper
* @author wangjc
* @date 2020-10-13 11:39:37
*/
public interface FileMapper extends BaseIMapper<FileModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<FileView> selectPage(FileDto dto);

    /**
     * 根据条件加载图片
     * @param dto
     * @return
     */
    IPage<FileView> loadImageTable(FileDto dto);
}
