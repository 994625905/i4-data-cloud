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
* @date 2020-11-06 09:46:07
*/
@TableName("t_weekreport_process_cc")
public class WeekreportProcessCcModel extends BaseModel<WeekreportProcessCcModel> {

    private static final long serialVersionUID = 1604627167221L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 周报id
    */
    @TableField("weekreport_id")
    private Integer weekreportId;

    /**
    * 周报流程id
    */
    @TableField("weekreport_process_id")
    private Integer weekreportProcessId;

    /**
    * 抄送对象
    */
    @TableField("cc_user_id")
    private Integer ccUserId;

    /**
    * 0未阅读，1已阅读
    */
    @TableField("read")
    private Integer read;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setWeekreportId(Integer weekreportId) {
    this.weekreportId = weekreportId;
    }

    public Integer getWeekreportId() {
    return this.weekreportId;
    }

    public void setWeekreportProcessId(Integer weekreportProcessId) {
    this.weekreportProcessId = weekreportProcessId;
    }

    public Integer getWeekreportProcessId() {
    return this.weekreportProcessId;
    }

    public void setCcUserId(Integer ccUserId) {
    this.ccUserId = ccUserId;
    }

    public Integer getCcUserId() {
    return this.ccUserId;
    }

    public void setRead(Integer read) {
    this.read = read;
    }

    public Integer getRead() {
    return this.read;
    }

}
