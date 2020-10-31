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
* @date 2020-10-30 16:52:07
*/
@TableName("v_act_re_deploy_procdef")
public class VActReDeployProcdefModel extends BaseModel<VActReDeployProcdefModel> {

    private static final long serialVersionUID = 1604047927204L;
    /**
    * 
    */
    @TableField("procdef_id")
    private String procdefId;

    /**
    * 
    */
    @TableField("deployment_id")
    private String deploymentId;

    /**
    * 
    */
    @TableField("procdef_name")
    private String procdefName;

    /**
    * 
    */
    @TableField("deployment_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deploymentCreateTime;

    /**
    * 
    */
    @TableField("procdef_key")
    private String procdefKey;

    /**
    * 
    */
    @TableField("procdef_version")
    private Integer procdefVersion;

    /**
    * 
    */
    @TableField("resource_name")
    private String resourceName;

    /**
    * 
    */
    @TableField("img_name")
    private String imgName;


    public void setProcdefId(String procdefId) {
    this.procdefId = procdefId;
    }

    public String getProcdefId() {
    return this.procdefId;
    }

    public void setDeploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
    }

    public String getDeploymentId() {
    return this.deploymentId;
    }

    public void setProcdefName(String procdefName) {
    this.procdefName = procdefName;
    }

    public String getProcdefName() {
    return this.procdefName;
    }

    public void setDeploymentCreateTime(Date deploymentCreateTime) {
    this.deploymentCreateTime = deploymentCreateTime;
    }

    public Date getDeploymentCreateTime() {
    return this.deploymentCreateTime;
    }

    public void setProcdefKey(String procdefKey) {
    this.procdefKey = procdefKey;
    }

    public String getProcdefKey() {
    return this.procdefKey;
    }

    public void setProcdefVersion(Integer procdefVersion) {
    this.procdefVersion = procdefVersion;
    }

    public Integer getProcdefVersion() {
    return this.procdefVersion;
    }

    public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
    }

    public String getResourceName() {
    return this.resourceName;
    }

    public void setImgName(String imgName) {
    this.imgName = imgName;
    }

    public String getImgName() {
    return this.imgName;
    }

}
