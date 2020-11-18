package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityView;

/**
* Mapper
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public interface PartyActivityMapper extends BaseIMapper<PartyActivityModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityView> selectPage(PartyActivityDto dto);

    /**
     * 查询活动行
     * @param dto
     * @return
     */
    IPage<PartyActivityView> selectActivity(PartyActivityDto dto);

    /**
     * 查询活动根据id
     * @param id
     * @return
     */
    PartyActivityView selectActivityById(@Param("id") Integer id);

    /**
     * 查询照片组
     * @return
     */
    List<PartyActivityView> selectImageGroup();

}
