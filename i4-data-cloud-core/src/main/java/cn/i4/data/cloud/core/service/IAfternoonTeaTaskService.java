package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.AfternoonTeaTaskDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaTaskModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaTaskView;

/**
* Service
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public interface IAfternoonTeaTaskService extends BaseService<AfternoonTeaTaskModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<AfternoonTeaTaskView> selectPage(AfternoonTeaTaskDto dto);

    /**
     * 查询任务待发布
     * @param dto
     * @return
     */
    IPage<AfternoonTeaTaskView> selectTaskDeploy(AfternoonTeaTaskDto dto);

    /**
     * 根据id删除，级联删除menu
     * @param id
     * @return
     * @throws CommonException
     */
    Boolean deleteById(Integer id) throws CommonException;

    /**
     * 新增/修改点单任务
     * @param dto
     */
    Boolean insert(AfternoonTeaTaskDto dto) throws CommonException;

    /**
     * 修改点单任务
     * @param dto
     * @return
     * @throws CommonException
     */
    Boolean update(AfternoonTeaTaskDto dto) throws CommonException;

}
