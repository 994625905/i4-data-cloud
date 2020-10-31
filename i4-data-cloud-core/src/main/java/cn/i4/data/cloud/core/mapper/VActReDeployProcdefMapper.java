package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.VActReDeployProcdefDto;
import cn.i4.data.cloud.core.entity.model.VActReDeployProcdefModel;
import cn.i4.data.cloud.core.entity.view.VActReDeployProcdefView;

/**
* Mapper
* @author wangjc
* @date 2020-10-30 16:52:07
*/
public interface VActReDeployProcdefMapper extends BaseIMapper<VActReDeployProcdefModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<VActReDeployProcdefView> selectPage(VActReDeployProcdefDto dto);

}
