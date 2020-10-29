package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AutocodeDatasourceModel;
import cn.i4.data.cloud.core.entity.view.AutocodeDatasourceView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public class AutocodeDatasourceDto extends BaseDto<AutocodeDatasourceView> {

    private static final long serialVersionUID = 1603618632376L;

    private Integer id;

    private String driverClass;
    private String dataSourceUrl;
    private String user;
    private String password;
    private String defaultAuthor;
    private String defaultPackagePrefix;
    private String defaultLocal;
    private List<String> tableList;

    private AutocodeDatasourceModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public void setDataSourceUrl(String dataSourceUrl) {
        this.dataSourceUrl = dataSourceUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AutocodeDatasourceModel getModel() {
        return model;
    }

    public void setModel(AutocodeDatasourceModel model) {
        this.model = model;
    }

    public String getDefaultAuthor() {
        return defaultAuthor;
    }

    public void setDefaultAuthor(String defaultAuthor) {
        this.defaultAuthor = defaultAuthor;
    }

    public String getDefaultPackagePrefix() {
        return defaultPackagePrefix;
    }

    public void setDefaultPackagePrefix(String defaultPackagePrefix) {
        this.defaultPackagePrefix = defaultPackagePrefix;
    }

    public String getDefaultLocal() {
        return defaultLocal;
    }

    public void setDefaultLocal(String defaultLocal) {
        this.defaultLocal = defaultLocal;
    }

    public List<String> getTableList() {
        return tableList;
    }

    public void setTableList(List<String> tableList) {
        this.tableList = tableList;
    }
}
