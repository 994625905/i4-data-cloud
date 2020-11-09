package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.LogPermissionErrorView;

/**
* Dto
* @author wangjc
* @date 2020-11-09 16:49:56
*/
public class LogPermissionErrorDto extends BaseDto<LogPermissionErrorView> {

    private static final long serialVersionUID = 1604911797098L;

    private String requestPath;

    private Integer type;

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
