package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.WeekreportModel;
import cn.i4.data.cloud.core.entity.view.WeekreportView;

/**
* Dto
* @author wangjc
* @date 2020-11-06 09:46:07
*/
public class WeekreportDto extends BaseDto<WeekreportView> {

    private static final long serialVersionUID = 1604627167261L;

    private Integer id;

    private Integer processId;

    private String title;

    private String titleTemplate;

    private WeekreportModel model;

    private String content;

    private String mdContent;

    private String mongoId;

    /** 过滤条件 */
    private String year;
    private String month;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleTemplate() {
        return titleTemplate;
    }

    public void setTitleTemplate(String titleTemplate) {
        this.titleTemplate = titleTemplate;
    }

    public WeekreportModel getModel() {
        return model;
    }

    public void setModel(WeekreportModel model) {
        this.model = model;
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

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
