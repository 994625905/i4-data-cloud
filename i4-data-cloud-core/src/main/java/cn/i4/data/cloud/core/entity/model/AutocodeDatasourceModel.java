package cn.i4.data.cloud.core.entity.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.i4.data.cloud.base.entity.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* Model
* @author wangjc
* @date 2020-10-11 13:42:24
*/
@TableName("t_autocode_datasource")
public class AutocodeDatasourceModel extends BaseModel<AutocodeDatasourceModel> {

    private static final long serialVersionUID = 1602394944521L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 数据源名称（不可重复）
    */
    @TableField("datasource_name")
    private String datasourceName;

    /**
    * 数据源链接
    */
    @TableField("datasource_url")
    private String datasourceUrl;

    /**
    * 数据库名称
    */
    @TableField("database_name")
    private String databaseName;

    /**
    * 驱动名称
    */
    @TableField("driverclass_name")
    private String driverclassName;

    /**
    * 驱动类型（MySQL，Oracle……）
    */
    @TableField("driverclass_type")
    private String driverclassType;

    /**
    * 授权的用户名
    */
    @TableField("auth_user")
    private String authUser;

    /**
    * 
    */
    @TableField("auth_password")
    private String authPassword;

    /**
    * 生成代码的默认作者
    */
    @TableField("default_author")
    private String defaultAuthor;

    /**
    * 默认的包前缀
    */
    @TableField("default_package_prefix")
    private String defaultPackagePrefix;

    /**
    * 默认存放的地址
    */
    @TableField("default_local")
    private String defaultLocal;

    /**
    * 
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 
    */
    @TableField("update_time")
    private Long updateTime;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setDatasourceName(String datasourceName) {
    this.datasourceName = datasourceName;
    }

    public String getDatasourceName() {
    return this.datasourceName;
    }

    public void setDatasourceUrl(String datasourceUrl) {
    this.datasourceUrl = datasourceUrl;
    }

    public String getDatasourceUrl() {
    return this.datasourceUrl;
    }

    public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
    }

    public String getDatabaseName() {
    return this.databaseName;
    }

    public void setDriverclassName(String driverclassName) {
    this.driverclassName = driverclassName;
    }

    public String getDriverclassName() {
    return this.driverclassName;
    }

    public void setDriverclassType(String driverclassType) {
    this.driverclassType = driverclassType;
    }

    public String getDriverclassType() {
    return this.driverclassType;
    }

    public void setAuthUser(String authUser) {
    this.authUser = authUser;
    }

    public String getAuthUser() {
    return this.authUser;
    }

    public void setAuthPassword(String authPassword) {
    this.authPassword = authPassword;
    }

    public String getAuthPassword() {
    return this.authPassword;
    }

    public void setDefaultAuthor(String defaultAuthor) {
    this.defaultAuthor = defaultAuthor;
    }

    public String getDefaultAuthor() {
    return this.defaultAuthor;
    }

    public void setDefaultPackagePrefix(String defaultPackagePrefix) {
    this.defaultPackagePrefix = defaultPackagePrefix;
    }

    public String getDefaultPackagePrefix() {
    return this.defaultPackagePrefix;
    }

    public void setDefaultLocal(String defaultLocal) {
    this.defaultLocal = defaultLocal;
    }

    public String getDefaultLocal() {
    return this.defaultLocal;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    }

    public Long getUpdateTime() {
    return this.updateTime;
    }

}
