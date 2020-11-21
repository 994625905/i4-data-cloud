package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.LogLimitDto;
import cn.i4.data.cloud.core.entity.model.LogLimitModel;
import cn.i4.data.cloud.core.entity.view.LogLimitView;

import java.util.Set;

/**
* Service
* @author wangjc
* @date 2020-10-28 19:40:49
*/
public interface ILogLimitService extends BaseService<LogLimitModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<LogLimitView> selectPage(LogLimitDto dto);

    /**
     * 同步redis数据
     * @param limitSet
     * @return
     */
    Integer asyncByRedis(Set<Object> limitSet);
}
