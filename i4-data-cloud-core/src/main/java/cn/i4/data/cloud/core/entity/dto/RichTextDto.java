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

    /**
     * 富文本内容
     */
    private String mdContent;
    private String content;

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

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
