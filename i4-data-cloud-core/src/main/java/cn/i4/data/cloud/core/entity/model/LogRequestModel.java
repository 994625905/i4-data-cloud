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
* @date 2020-10-25 17:37:12
*/
@TableName("t_log_request")
public class LogRequestModel extends BaseModel<LogRequestModel> {

    private static final long serialVersionUID = 1603618632627L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 
    */
    @TableField("login_name")
    private String loginName;

    /**
    * 请求IP
    */
    @TableField("request_ip")
    private String requestIp;

    /**
    * 星期几
    */
    @TableField("week")
    private String week;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 请求模块
    */
    @TableField("module_name")
    private String moduleName;

    /**
    * 请求内容
    */
    @TableField("action_content")
    private String actionContent;

    /**
    * 请求方法名称
    */
    @TableField("action_method")
    private String actionMethod;

    /**
    * 1增，2删，3改，4查
    */
    @TableField("action_type")
    private Integer actionType;

    /**
    * 1增，2删，3改，4查
    */
    @TableField("action_name")
    private String actionName;

    /**
    * 异常原因
    */
    @TableField("action_exception")
    private String actionException;

    /**
    * 执行时间
    */
    @TableField("action_time")
    private Long actionTime;

    /**
    * 1成功，2异常失败
    */
    @TableField("action_result")
    private Integer actionResult;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
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

    public void setRequestIp(String requestIp) {
    this.requestIp = requestIp;
    }

    public String getRequestIp() {
    return this.requestIp;
    }

    public void setWeek(String week) {
    this.week = week;
    }

    public String getWeek() {
    return this.week;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
    }

    public String getModuleName() {
    return this.moduleName;
    }

    public void setActionContent(String actionContent) {
    this.actionContent = actionContent;
    }

    public String getActionContent() {
    return this.actionContent;
    }

    public void setActionMethod(String actionMethod) {
    this.actionMethod = actionMethod;
    }

    public String getActionMethod() {
    return this.actionMethod;
    }

    public void setActionType(Integer actionType) {
    this.actionType = actionType;
    }

    public Integer getActionType() {
    return this.actionType;
    }

    public void setActionName(String actionName) {
    this.actionName = actionName;
    }

    public String getActionName() {
    return this.actionName;
    }

    public void setActionException(String actionException) {
    this.actionException = actionException;
    }

    public String getActionException() {
    return this.actionException;
    }

    public void setActionTime(Long actionTime) {
    this.actionTime = actionTime;
    }

    public Long getActionTime() {
    return this.actionTime;
    }

    public void setActionResult(Integer actionResult) {
    this.actionResult = actionResult;
    }

    public Integer getActionResult() {
    return this.actionResult;
    }

}
