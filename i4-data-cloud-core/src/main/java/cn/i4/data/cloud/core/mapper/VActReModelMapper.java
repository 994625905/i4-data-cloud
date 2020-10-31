package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.VActReModelDto;
import cn.i4.data.cloud.core.entity.model.VActReModelModel;
import cn.i4.data.cloud.core.entity.view.VActReModelView;

/**
* Mapper
* @author wangjc
* @date 2020-10-30 16:52:07
*/
public interface VActReModelMapper extends BaseIMapper<VActReModelModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<VActReModelView> selectPage(VActReModelDto dto);

}
