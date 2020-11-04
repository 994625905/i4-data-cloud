package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.RichTextModel;
import cn.i4.data.cloud.core.entity.view.RichTextView;

/**
* Dto
* @author wangjc
* @date 2020-11-04 15:55:45
*/
public class RichTextDto extends BaseDto<RichTextView> {

    private static final long serialVersionUID = 1604476546109L;

    private Integer id;

    private String mongoId;

    private RichTextModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public RichTextModel getModel() {
        return model;
    }

    public void setModel(RichTextModel model) {
        this.model = model;
    }
}
