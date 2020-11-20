package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import cn.i4.data.cloud.core.entity.dto.PartyActivityImageDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.PartyActivityDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public interface IPartyActivityService extends BaseService<PartyActivityModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<PartyActivityView> selectPage(PartyActivityDto dto);

    /**
     * 查询活动行
     * @param dto
     * @return
     */
    IPage<PartyActivityView> selectActivity(PartyActivityDto dto);

    /**
     * 删除，级联删除，清空数据
     * @param id
     * @return
     */
    Boolean deleteById(Integer id) throws CommonException;

    /**
     * 新增
     * @param dto
     * @return
     */
    Boolean insert(PartyActivityDto dto) throws CommonException;

    /**
     * 修改
     * @param dto
     * @return
     */
    Boolean update(PartyActivityDto dto) throws CommonException;

    /**
     * 根据id查询（手动SQL）
     * @param id
     * @return
     */
    PartyActivityView selectById(Integer id);

    /**
     * 加载照片组
     * @return
     */
    IPage<PartyActivityView> selectImageGroup(PartyActivityImageDto dto);

}
