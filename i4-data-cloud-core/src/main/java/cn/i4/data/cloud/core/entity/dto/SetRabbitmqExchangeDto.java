package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqExchangeModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqExchangeView;

/**
* Dto
* @author wangjc
* @date 2020-10-24 13:57:40
*/
public class SetRabbitmqExchangeDto extends BaseDto<SetRabbitmqExchangeView> {

    private static final long serialVersionUID = 1603519061358L;

    private Integer id;

    private SetRabbitmqExchangeModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SetRabbitmqExchangeModel getModel() {
        return model;
    }

    public void setModel(SetRabbitmqExchangeModel model) {
        this.model = model;
    }
}
