package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageLikeModel;
import cn.i4.data.cloud.core.entity.model.PartyActivityImageReadModel;
import cn.i4.data.cloud.core.entity.view.FileView;
import cn.i4.data.cloud.core.entity.view.PartyActivityImageView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public class PartyActivityImageDto extends BaseDto<PartyActivityImageView> {

    private static final long serialVersionUID = 1605334833912L;

    private Integer id;

    /**
     * 活动id，标题
     */
    private Integer activityId;
    private String activityTitle;

    /**
     * 上传照片list
     */
    private List<FileView> imageList;

    /**
     * 阅读，点赞
     */
    private PartyActivityImageReadModel readModel;
    private PartyActivityImageLikeModel likeModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public List<FileView> getImageList() {
        return imageList;
    }

    public void setImageList(List<FileView> imageList) {
        this.imageList = imageList;
    }

    public PartyActivityImageReadModel getReadModel() {
        return readModel;
    }

    public void setReadModel(PartyActivityImageReadModel readModel) {
        this.readModel = readModel;
    }

    public PartyActivityImageLikeModel getLikeModel() {
        return likeModel;
    }

    public void setLikeModel(PartyActivityImageLikeModel likeModel) {
        this.likeModel = likeModel;
    }
}
