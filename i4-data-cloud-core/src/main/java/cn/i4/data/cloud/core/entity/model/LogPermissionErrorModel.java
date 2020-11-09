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
* @date 2020-11-09 16:49:56
*/
@TableName("t_log_permission_error")
public class LogPermissionErrorModel extends BaseModel<LogPermissionErrorModel> {

    private static final long serialVersionUID = 1604911797009L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 类名称
    */
    @TableField("class_name")
    private String className;

    /**
    * 方法名称
    */
    @TableField("method_name")
    private String methodName;

    /**
    * 请求路径
    */
    @TableField("request_path")
    private String requestPath;

    /**
    * 权限标识符
    */
    @TableField("permission")
    private String permission;

    /**
    * 类型，0页面，1数据接口
    */
    @TableField("type")
    private Integer type;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 拦截用户
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 拦截用户名
    */
    @TableField("login_name")
    private String loginName;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setClassName(String className) {
    this.className = className;
    }

    public String getClassName() {
    return this.className;
    }

    public void setMethodName(String methodName) {
    this.methodName = methodName;
    }

    public String getMethodName() {
    return this.methodName;
    }

    public void setRequestPath(String requestPath) {
    this.requestPath = requestPath;
    }

    public String getRequestPath() {
    return this.requestPath;
    }

    public void setPermission(String permission) {
    this.permission = permission;
    }

    public String getPermission() {
    return this.permission;
    }

    public void setType(Integer type) {
    this.type = type;
    }

    public Integer getType() {
    return this.type;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setUserId(Integer userId) {
    this.userId = userId;
    }

    public Integer getUserId() {
    return this.userId;
    }

    public void setLoginName(String loginName) {
    this.loginName = loginName;
    }

    public String getLoginName() {
    return this.loginName;
    }

}
