package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeView;

/**
* Service
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public interface IHistoryNoticeService extends BaseService<HistoryNoticeModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryNoticeView> selectPage(HistoryNoticeDto dto);

    /**
     * 删除公告，级联删除
     * @param id
     * @return
     * @throws CommonException
     */
    Boolean deleteById(Integer id) throws CommonException;

    /**
     * 新增公告
     * @param dto
     * @return
     */
    Boolean insert(HistoryNoticeDto dto) throws CommonException;

    /**
     * 修改公告
     * @param dto
     * @return
     */
    Boolean update(HistoryNoticeDto dto) throws CommonException;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    HistoryNoticeView selectById(Integer id);
}
