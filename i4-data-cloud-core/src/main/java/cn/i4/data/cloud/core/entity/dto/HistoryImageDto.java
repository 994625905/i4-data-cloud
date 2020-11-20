package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageLikeModel;
import cn.i4.data.cloud.core.entity.model.HistoryImageReadModel;
import cn.i4.data.cloud.core.entity.view.FileView;
import cn.i4.data.cloud.core.entity.view.HistoryImageView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public class HistoryImageDto extends BaseDto<HistoryImageView> {

    private static final long serialVersionUID = 1605786424871L;

    private Integer id;

    private Integer groupId;
    private String groupName;

    /**
     * 上传照片list
     */
    private List<FileView> imageList;

    /**
     * 阅读，点赞
     */
    private HistoryImageReadModel readModel;
    private HistoryImageLikeModel likeModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<FileView> getImageList() {
        return imageList;
    }

    public void setImageList(List<FileView> imageList) {
        this.imageList = imageList;
    }

    public HistoryImageReadModel getReadModel() {
        return readModel;
    }

    public void setReadModel(HistoryImageReadModel readModel) {
        this.readModel = readModel;
    }

    public HistoryImageLikeModel getLikeModel() {
        return likeModel;
    }

    public void setLikeModel(HistoryImageLikeModel likeModel) {
        this.likeModel = likeModel;
    }
}
