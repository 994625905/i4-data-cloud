package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.LogLimitView;

/**
* Dto
* @author wangjc
* @date 2020-10-28 19:40:49
*/
public class LogLimitDto extends BaseDto<LogLimitView> {

    private static final long serialVersionUID = 1603885249712L;

    /**
     * 请求路径
     */
    private String requestPath;
    /**
     * 类型
     */
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
