package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.WeekreportDto;
import cn.i4.data.cloud.core.entity.model.WeekreportModel;
import cn.i4.data.cloud.core.entity.view.WeekreportView;

/**
* Service
* @author wangjc
* @date 2020-11-06 09:46:07
*/
public interface IWeekreportService extends BaseService<WeekreportModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<WeekreportView> selectPage(WeekreportDto dto);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    WeekreportView selectRealNameById(Integer id);

    /**
     * 查询所有
     * @param dto
     * @return
     */
    IPage<WeekreportView> selectAll(WeekreportDto dto);

    /**
     * 新增周报
     * @param dto
     * @return
     * @throws CommonException
     */
    Boolean insert(WeekreportDto dto) throws CommonException;

    /**
     * 修改周报
     * @param dto
     * @return
     */
    Boolean update(WeekreportDto dto) throws CommonException;

    /**
     * 删除周报
     * @param id
     * @return
     */
    Boolean deleteById(Integer id) throws CommonException;
}
