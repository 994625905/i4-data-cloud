package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.InviteCodeDto;
import cn.i4.data.cloud.core.entity.model.InviteCodeModel;
import cn.i4.data.cloud.core.entity.view.InviteCodeView;

/**
* Service
* @author wangjc
* @date 2020-10-18 14:34:31
*/
public interface IInviteCodeService extends BaseService<InviteCodeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<InviteCodeView> selectPage(InviteCodeDto dto);

}
