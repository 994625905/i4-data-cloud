package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaSelectDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaSelectModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public interface IAfternoonTeaSelectService extends BaseService<AfternoonTeaSelectModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaSelectView> selectPage(AfternoonTeaSelectDto dto);

    /**
     * 点单选择
     * @param dto
     * @return
     */
    Boolean order(AfternoonTeaSelectDto dto) throws CommonException;

    /**
     * 根据任务id获取选择列表
     * @param taskId
     * @return
     */
    List<AfternoonTeaSelectModel> getListByTaskId(Integer taskId);

    /**
     * 加载详情页的表格
     * @param dto
     * @return
     */
    IPage<AfternoonTeaSelectView> selectDetailTable(AfternoonTeaSelectDto dto);
}
