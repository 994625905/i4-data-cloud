package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.UserTemplateDto;
import cn.i4.data.cloud.core.entity.model.UserTemplateModel;
import cn.i4.data.cloud.core.entity.view.UserTemplateView;

/**
* Service
* @author wangjc
* @date 2020-11-06 15:24:53
*/
public interface IUserTemplateService extends BaseService<UserTemplateModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<UserTemplateView> selectPage(UserTemplateDto dto);

    /**
     * 根据用户Id查询
     * @param userId
     * @return
     */
    UserTemplateModel selectByUserId(Integer userId);

}
