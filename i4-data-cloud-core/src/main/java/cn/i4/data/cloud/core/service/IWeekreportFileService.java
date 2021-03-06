package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportFileDto;
import cn.i4.data.cloud.core.entity.model.WeekreportFileModel;
import cn.i4.data.cloud.core.entity.view.WeekreportFileView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-20 15:07:56
*/
public interface IWeekreportFileService extends BaseService<WeekreportFileModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<WeekreportFileView> selectPage(WeekreportFileDto dto);

    /**
     * 查询附件列表
     * @param weekReportId
     * @return
     */
    List<WeekreportFileView> selectByWeekReportId(Integer weekReportId);
}
