package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.PartyActivityFileModel;
import cn.i4.data.cloud.core.entity.model.PartyActivityModel;
import cn.i4.data.cloud.core.entity.view.PartyActivityView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-14 14:20:33
*/
public class PartyActivityDto extends BaseDto<PartyActivityView> {

    private static final long serialVersionUID = 1605334833871L;

    private Integer year;
    private Integer typeId;

    private Integer id;
    private Integer status;
    private String mongoId;

    /**
     * 内容，作用富文本编辑器
     */
    private String content;

    /**
     * md内容，作用Markdown编辑器
     */
    private String mdContent;

    private PartyActivityModel model;

    private List<PartyActivityFileModel> fileList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public PartyActivityModel getModel() {
        return model;
    }

    public void setModel(PartyActivityModel model) {
        this.model = model;
    }

    public List<PartyActivityFileModel> getFileList() {
        return fileList;
    }

    public void setFileList(List<PartyActivityFileModel> fileList) {
        this.fileList = fileList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
