package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.SystemConstantDto;
import cn.i4.data.cloud.core.entity.model.SystemConstantModel;
import cn.i4.data.cloud.core.entity.view.SystemConstantView;

import java.util.Map;

/**
* Service
* @author wangjc
* @date 2020-10-14 14:43:30
*/
public interface ISystemConstantService extends BaseService<SystemConstantModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<SystemConstantView> selectPage(SystemConstantDto dto);

    /**
     * 获取系统常量信息
     * @return
     */
    Map<String, Object> getSystemConstant();

}
