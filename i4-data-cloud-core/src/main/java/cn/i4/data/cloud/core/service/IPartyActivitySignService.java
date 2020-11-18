package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivitySignDto;
import cn.i4.data.cloud.core.entity.model.PartyActivitySignModel;
import cn.i4.data.cloud.core.entity.view.PartyActivitySignView;

import java.util.Map;

/**
* Service
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public interface IPartyActivitySignService extends BaseService<PartyActivitySignModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivitySignView> selectPage(PartyActivitySignDto dto);

    /**
     * 签到的统计信息
     * @param activityId
     * @return
     */
    Map<String, Object> signCount(Integer activityId);

}
