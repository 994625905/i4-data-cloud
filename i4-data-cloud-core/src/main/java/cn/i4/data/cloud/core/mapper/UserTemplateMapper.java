package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.UserTemplateDto;
import cn.i4.data.cloud.core.entity.model.UserTemplateModel;
import cn.i4.data.cloud.core.entity.view.UserTemplateView;

/**
* Mapper
* @author wangjc
* @date 2020-11-06 15:24:53
*/
public interface UserTemplateMapper extends BaseIMapper<UserTemplateModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<UserTemplateView> selectPage(UserTemplateDto dto);

}
