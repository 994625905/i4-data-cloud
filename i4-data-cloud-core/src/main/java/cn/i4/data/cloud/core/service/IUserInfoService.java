package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.core.entity.dto.UserInfoDto;
import cn.i4.data.cloud.core.entity.model.UserInfoModel;
import cn.i4.data.cloud.core.entity.view.UserInfoView;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
* Service
* @author wangjc
* @date 2020-10-10 10:14:12
*/
public interface IUserInfoService extends BaseService<UserInfoModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<UserInfoView> selectPage(UserInfoDto dto);

}
