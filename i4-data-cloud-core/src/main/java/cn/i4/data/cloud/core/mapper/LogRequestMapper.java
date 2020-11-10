package cn.i4.data.cloud.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogRequestDto;
import cn.i4.data.cloud.core.entity.model.LogRequestModel;
import cn.i4.data.cloud.core.entity.view.LogRequestView;
import org.springframework.cglib.beans.BeanMap;

/**
* Mapper
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public interface LogRequestMapper extends BaseIMapper<LogRequestModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogRequestView> selectPage(LogRequestDto dto);

    /**
     * 根据日志分组查
     * @param beanMap
     * @return
     */
    List<Map<String, Object>> selectGroupByDate(BeanMap beanMap);

    /**
     * 查询个人日志
     * @param beanMap
     * @return
     */
    List<LogRequestView> selectByUserId(BeanMap beanMap);

}
