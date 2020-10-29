package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.LogAutocodeTableView;

/**
* Dto
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public class LogAutocodeTableDto extends BaseDto<LogAutocodeTableView> {

    private static final long serialVersionUID = 1603618632578L;

    private Integer autoCodeLogId;

    public Integer getAutoCodeLogId() {
        return autoCodeLogId;
    }

    public void setAutoCodeLogId(Integer autoCodeLogId) {
        this.autoCodeLogId = autoCodeLogId;
    }
}
