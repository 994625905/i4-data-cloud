package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.VActReModelView;

/**
* Dto
* @author wangjc
* @date 2020-10-30 16:52:07
*/
public class VActReModelDto extends BaseDto<VActReModelView> {

    private static final long serialVersionUID = 1604047927190L;

    /**
     * 模板ID
     */
    private String modelId;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
