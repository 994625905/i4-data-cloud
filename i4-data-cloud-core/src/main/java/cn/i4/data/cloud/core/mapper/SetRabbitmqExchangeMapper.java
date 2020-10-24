package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.SetRabbitmqExchangeDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqExchangeModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqExchangeView;

/**
* Mapper
* @author wangjc
* @date 2020-10-24 13:57:40
*/
public interface SetRabbitmqExchangeMapper extends BaseIMapper<SetRabbitmqExchangeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<SetRabbitmqExchangeView> selectPage(SetRabbitmqExchangeDto dto);

}
