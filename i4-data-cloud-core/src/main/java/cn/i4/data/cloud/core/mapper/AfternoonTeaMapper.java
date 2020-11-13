package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaView;

/**
* Mapper
* @author wangjc
* @date 2020-11-10 15:14:47
*/
public interface AfternoonTeaMapper extends BaseIMapper<AfternoonTeaModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaView> selectPage(AfternoonTeaDto dto);

    /**
     * 根据ids查询
     * @param asList
     * @return
     */
    List<AfternoonTeaView> selectByIds(@Param("idList") List<String> idList,@Param("menuId") Integer menuId,@Param("taskId") Integer taskId);
}
