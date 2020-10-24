package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.InviteCodeRoleDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeRoleModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeRoleView;

/**
* Mapper
* @author wangjc
* @date 2020-10-18 14:34:30
*/
public interface InviteCodeRoleMapper extends BaseIMapper<InviteCodeRoleModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<InviteCodeRoleView> selectPage(InviteCodeRoleDto dto);

}
