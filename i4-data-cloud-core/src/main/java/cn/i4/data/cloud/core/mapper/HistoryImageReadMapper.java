package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryImageReadDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageReadModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageReadView;

/**
* Mapper
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public interface HistoryImageReadMapper extends BaseIMapper<HistoryImageReadModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryImageReadView> selectPage(HistoryImageReadDto dto);

    /**
     * 删除组下照片的阅读数
     * @param groupId
     * @return
     */
    Integer deleteByGroupId(@Param("groupId") Integer groupId);
}
