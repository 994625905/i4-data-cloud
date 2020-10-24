package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.UserRoleDto;
import cn.i4.data.cloud.core.entity.model.UserRoleModel;
import cn.i4.data.cloud.core.entity.view.UserRoleView;

/**
* Mapper
* @author wangjc
* @date 2020-10-11 13:42:25
*/
public interface UserRoleMapper extends BaseIMapper<UserRoleModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<UserRoleView> selectPage(UserRoleDto dto);

}
