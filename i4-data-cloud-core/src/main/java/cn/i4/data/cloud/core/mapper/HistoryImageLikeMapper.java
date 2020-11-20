package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryImageLikeDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageLikeModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageLikeView;

/**
* Mapper
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public interface HistoryImageLikeMapper extends BaseIMapper<HistoryImageLikeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryImageLikeView> selectPage(HistoryImageLikeDto dto);

    /**
     * 删除组下照片的点赞数
     * @param groupId
     */
    Integer deleteByGroupId(@Param("groupId") Integer groupId);
}
