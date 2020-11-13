package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaSelectDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaSelectModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;

/**
* Mapper
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public interface AfternoonTeaSelectMapper extends BaseIMapper<AfternoonTeaSelectModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaSelectView> selectPage(AfternoonTeaSelectDto dto);

    /**
     * 加载详情页的表格
     * @param dto
     * @return
     */
    IPage<AfternoonTeaSelectView> selectDetailTable(AfternoonTeaSelectDto dto);
}
