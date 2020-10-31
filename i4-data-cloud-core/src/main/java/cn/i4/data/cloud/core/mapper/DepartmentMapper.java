package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.DepartmentDto;
import cn.i4.data.cloud.core.entity.model.DepartmentModel;
import cn.i4.data.cloud.core.entity.view.DepartmentView;

/**
* Mapper
* @author wangjc
* @date 2020-10-31 15:24:09
*/
public interface DepartmentMapper extends BaseIMapper<DepartmentModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<DepartmentView> selectPage(DepartmentDto dto);

    /**
     * 根据用户id获取所属的部门
     * @param userId
     * @return
     */
    DepartmentView getByUserId(@Param("userId") Integer userId);
}
