package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaMenuDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaMenuModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaMenuView;

/**
* Mapper
* @author wangjc
* @date 2020-11-11 11:41:50
*/
public interface AfternoonTeaMenuMapper extends BaseIMapper<AfternoonTeaMenuModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaMenuView> selectPage(AfternoonTeaMenuDto dto);

}
