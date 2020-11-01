package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.LeaveTypeView;

/**
* Dto
* @author wangjc
* @date 2020-11-01 14:58:27
*/
public class LeaveTypeDto extends BaseDto<LeaveTypeView> {

    private static final long serialVersionUID = 1604213907803L;

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
