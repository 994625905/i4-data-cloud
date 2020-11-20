package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryImageGroupDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageGroupModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageGroupView;

/**
* Service
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public interface IHistoryImageGroupService extends BaseService<HistoryImageGroupModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryImageGroupView> selectPage(HistoryImageGroupDto dto);

    /**
     * 删除，级联删除所有
     * @param dto
     * @return
     */
    Boolean deleteById(HistoryImageGroupDto dto) throws CommonException;

    /**
     * 根据相册id查询
     * @param id
     * @return
     */
    HistoryImageGroupView selectByGroupId(Integer id);

}
