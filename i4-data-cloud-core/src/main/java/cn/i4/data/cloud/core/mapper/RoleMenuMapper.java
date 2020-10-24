package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.RoleMenuDto;
import cn.i4.data.cloud.core.entity.model.RoleMenuModel;
import cn.i4.data.cloud.core.entity.view.RoleMenuView;

/**
* Mapper
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public interface RoleMenuMapper extends BaseIMapper<RoleMenuModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<RoleMenuView> selectPage(RoleMenuDto dto);

}
