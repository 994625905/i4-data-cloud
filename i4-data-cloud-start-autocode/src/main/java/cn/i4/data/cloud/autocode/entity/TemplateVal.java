package cn.i4.data.cloud.autocode.entity;

/**
 * @author AS065
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/27-18:09
 */
public class TemplateVal {

    private String ModelName;

    private String table_name;

    private String table_Comment;

    private String ModuleName;

    private String author;

    private String crateDate;

    private String serialVersionUID;

    private TableDto tableDto;

    private String mgnModule;
    private String pageModule;

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_Comment() {
        return table_Comment;
    }

    public void setTable_Comment(String table_Comment) {
        this.table_Comment = table_Comment;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(String crateDate) {
        this.crateDate = crateDate;
    }

    public String getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(String serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public TableDto getTableDto() {
        return tableDto;
    }

    public void setTableDto(TableDto tableDto) {
        this.tableDto = tableDto;
    }

    public String getMgnModule() {
        return mgnModule;
    }

    public void setMgnModule(String mgnModule) {
        this.mgnModule = mgnModule;
    }

    public String getPageModule() {
        return pageModule;
    }

    public void setPageModule(String pageModule) {
        this.pageModule = pageModule;
    }
}
