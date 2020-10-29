package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.SetRabbitmqQueueModel;
import cn.i4.data.cloud.core.entity.view.SetRabbitmqQueueView;

/**
* Dto
* @author wangjc
* @date 2020-10-24 13:57:41
*/
public class SetRabbitmqQueueDto extends BaseDto<SetRabbitmqQueueView> {

    private static final long serialVersionUID = 1603519061505L;

    private Integer id;

    private SetRabbitmqQueueModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SetRabbitmqQueueModel getModel() {
        return model;
    }

    public void setModel(SetRabbitmqQueueModel model) {
        this.model = model;
    }
}
