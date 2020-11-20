package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryImageGroupDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageGroupModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageGroupView;

/**
* Mapper
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public interface HistoryImageGroupMapper extends BaseIMapper<HistoryImageGroupModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryImageGroupView> selectPage(HistoryImageGroupDto dto);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    HistoryImageGroupView selectByGroupId(@Param("id") Integer id);

}
