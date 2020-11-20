package cn.i4.data.cloud.core.service;

import cn.i4.data.cloud.base.exception.CommonException;
import cn.i4.data.cloud.base.service.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.i4.data.cloud.core.entity.dto.HistoryImageDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageView;

import java.util.List;

/**
* Service
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public interface IHistoryImageService extends BaseService<HistoryImageModel> {

    /**
    * 分页查询
    * @param dto
    * @return
    */
    IPage<HistoryImageView> selectPage(HistoryImageDto dto);

    /**
     * 根据groupId查询
     * @param groupId
     * @return
     */
    List<HistoryImageModel> selectByGroupId(Integer groupId);


    /**
     * 上传
     * @param dto
     * @return
     */
    List<HistoryImageView> uploadImage(HistoryImageDto dto) throws CommonException;

    /**
     * 删除照片
     * @param dto
     * @return
     * @throws CommonException
     */
    Boolean deleteById(HistoryImageDto dto) throws CommonException;
}
