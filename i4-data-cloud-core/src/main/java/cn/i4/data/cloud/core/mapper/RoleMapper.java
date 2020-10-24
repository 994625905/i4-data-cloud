package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.RoleDto;
import cn.i4.data.cloud.core.entity.model.RoleModel;
import cn.i4.data.cloud.core.entity.view.RoleView;

/**
* Mapper
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public interface RoleMapper extends BaseIMapper<RoleModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<RoleView> selectPage(RoleDto dto);

    /**
     * 根据用户ID查所拥有的的角色
     * @param userId
     * @return
     */
    List<RoleView> selectRolesByUserId(@Param("userId") Integer userId);
}
