package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.InviteCodeDepartmentDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeDepartmentModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeDepartmentView;

/**
* Mapper
* @author wangjc
* @date 2020-10-31 17:10:53
*/
public interface InviteCodeDepartmentMapper extends BaseIMapper<InviteCodeDepartmentModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<InviteCodeDepartmentView> selectPage(InviteCodeDepartmentDto dto);

}
