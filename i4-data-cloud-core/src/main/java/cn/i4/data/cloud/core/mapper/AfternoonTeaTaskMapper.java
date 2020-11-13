package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;

/**
* Mapper
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public interface AfternoonTeaTaskMapper extends BaseIMapper<AfternoonTeaTaskModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaTaskView> selectPage(AfternoonTeaTaskDto dto);

    /**
     * 查询任务待发布
     * @param dto
     * @return
     */
    IPage<AfternoonTeaTaskView> selectTaskDeploy(AfternoonTeaTaskDto dto);
}
