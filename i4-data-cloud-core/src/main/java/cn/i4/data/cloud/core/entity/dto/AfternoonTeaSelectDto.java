package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaSelectView;

/**
* Dto
* @author wangjc
* @date 2020-11-10 19:54:04
*/
public class AfternoonTeaSelectDto extends BaseDto<AfternoonTeaSelectView> {

    private static final long serialVersionUID = 1605009244128L;

    private String teaName;

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }
}
