package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeFileModel;
import cn.i4.data.cloud.core.entity.model.HistoryNoticeModel;
import cn.i4.data.cloud.core.entity.view.HistoryNoticeView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-11-21 14:11:04
*/
public class HistoryNoticeDto extends BaseDto<HistoryNoticeView> {

    private static final long serialVersionUID = 1605939064272L;

    private Integer id;

    private String mongoId;

    private HistoryNoticeModel model;

    private List<HistoryNoticeFileModel> fileList;

    private String content;

    private String mdContent;

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

    public HistoryNoticeModel getModel() {
        return model;
    }

    public void setModel(HistoryNoticeModel model) {
        this.model = model;
    }

    public List<HistoryNoticeFileModel> getFileList() {
        return fileList;
    }

    public void setFileList(List<HistoryNoticeFileModel> fileList) {
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
}
