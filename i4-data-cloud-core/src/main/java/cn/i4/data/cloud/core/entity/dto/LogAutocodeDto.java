package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.LogAutocodeView;

/**
* Dto
* @author wangjc
* @date 2020-10-25 17:37:12
*/
public class LogAutocodeDto extends BaseDto<LogAutocodeView> {

    private static final long serialVersionUID = 1603618632515L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
