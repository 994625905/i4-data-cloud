package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.VActReDeployProcdefView;

/**
* Dto
* @author wangjc
* @date 2020-10-30 16:52:07
*/
public class VActReDeployProcdefDto extends BaseDto<VActReDeployProcdefView> {

    private static final long serialVersionUID = 1604047927212L;

    private String procdefId;

    private String deploymentId;

    private String imgName;

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getProcdefId() {
        return procdefId;
    }

    public void setProcdefId(String procdefId) {
        this.procdefId = procdefId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
