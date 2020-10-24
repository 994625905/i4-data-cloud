package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.InviteCodeDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeView;

/**
* Mapper
* @author wangjc
* @date 2020-10-18 14:34:31
*/
public interface InviteCodeMapper extends BaseIMapper<InviteCodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<InviteCodeView> selectPage(InviteCodeDto dto);

}
