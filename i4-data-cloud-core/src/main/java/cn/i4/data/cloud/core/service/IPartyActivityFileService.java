package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityFileDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityFileModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityFileView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-14 15:39:24
*/
public interface IPartyActivityFileService extends BaseService<PartyActivityFileModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityFileView> selectPage(PartyActivityFileDto dto);

    /**
     * 根据activityId查询附件列表
     * @param activityId
     * @return
     */
    List<PartyActivityFileModel> getListByActivityId(Integer activityId);
}
