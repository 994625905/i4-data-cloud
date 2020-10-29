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
@TableName("t_log_autocode_table")
public class LogAutocodeTableModel extends BaseModel<LogAutocodeTableModel> {

    private static final long serialVersionUID = 1603618632567L;
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
    * 生成日志ID
    */
    @TableField("autocode_log_id")
    private Integer autocodeLogId;

    /**
    * 
    */
    @TableField("table_name")
    private String tableName;

    /**
    * 
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 0成功，1失败
    */
    @TableField("create_result")
    private Integer createResult;


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

    public void setAutocodeLogId(Integer autocodeLogId) {
    this.autocodeLogId = autocodeLogId;
    }

    public Integer getAutocodeLogId() {
    return this.autocodeLogId;
    }

    public void setTableName(String tableName) {
    this.tableName = tableName;
    }

    public String getTableName() {
    return this.tableName;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setCreateResult(Integer createResult) {
    this.createResult = createResult;
    }

    public Integer getCreateResult() {
    return this.createResult;
    }

}
