package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportProcessNodeDto;
import cn.i4.data.cloud.core.entity.model.WeekreportProcessNodeModel;
import cn.i4.data.cloud.core.entity.view.WeekreportProcessNodeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-06 09:46:07
*/
public interface WeekreportProcessNodeMapper extends BaseIMapper<WeekreportProcessNodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<WeekreportProcessNodeView> selectPage(WeekreportProcessNodeDto dto);

    /**
     * 加载流程办理节点列表
     * @param processId
     * @return
     */
    List<WeekreportProcessNodeView> selectByProcessId(@Param("processId") Integer processId);
}
