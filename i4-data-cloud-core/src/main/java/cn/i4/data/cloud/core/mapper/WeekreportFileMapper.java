package cn.i4.data.cloud.core.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportFileDto;
import cn.i4.data.cloud.core.entity.model.WeekreportFileModel;
import cn.i4.data.cloud.core.entity.view.WeekreportFileView;

/**
* Mapper
* @author wangjc
* @date 2020-11-20 15:07:56
*/
public interface WeekreportFileMapper extends BaseIMapper<WeekreportFileModel> {

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
    List<WeekreportFileView> selectByWeekReportId(@Param("weekReportId") Integer weekReportId);
}
