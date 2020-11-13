package cn.i4.data.cloud.core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import cn.i4.data.cloud.base.mapper.BaseIMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaSelectDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaSelectModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;
import org.springframework.cglib.beans.BeanMap;

/**
* Mapper
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public interface AfternoonTeaSelectMapper extends BaseIMapper<AfternoonTeaSelectModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaSelectView> selectPage(AfternoonTeaSelectDto dto);

    /**
     * 加载详情页的表格
     * @param dto
     * @return
     */
    IPage<AfternoonTeaSelectView> selectDetailTable(AfternoonTeaSelectDto dto);

    /**
     * 加载报表
     * @param beanMap
     * @return
     */
    List<Map<String, Object>> loadChart(BeanMap beanMap);

    /**
     * 加载报表根据类型
     * @param beanMap
     * @return
     */
    List<Map<String, Object>> loadChartByType(BeanMap beanMap);

}
