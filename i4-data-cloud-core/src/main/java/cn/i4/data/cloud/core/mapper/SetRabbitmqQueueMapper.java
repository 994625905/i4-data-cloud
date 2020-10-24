package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqQueueDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqQueueView;

/**
* Mapper
* @author wangjc
* @date 2020-10-24 13:57:41
*/
public interface SetRabbitmqQueueMapper extends BaseIMapper<SetRabbitmqQueueModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<SetRabbitmqQueueView> selectPage(SetRabbitmqQueueDto dto);

}
