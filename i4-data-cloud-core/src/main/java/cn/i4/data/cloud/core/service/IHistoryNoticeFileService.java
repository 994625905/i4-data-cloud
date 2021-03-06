package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryNoticeFileDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeFileModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeFileView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public interface IHistoryNoticeFileService extends BaseService<HistoryNoticeFileModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryNoticeFileView> selectPage(HistoryNoticeFileDto dto);

    /**
     * 根据公告id查询附件列表
     * @param noticeId
     * @return
     */
    List<HistoryNoticeFileView> selectByNoticeId(Integer noticeId);
}
