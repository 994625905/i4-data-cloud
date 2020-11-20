package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.HistoryImageGroupModel;
import cn.i4.data.cloud.core.entity.view.HistoryImageGroupView;

/**
* Dto
* @author wangjc
* @date 2020-11-19 19:47:04
*/
public class HistoryImageGroupDto extends BaseDto<HistoryImageGroupView> {

    private static final long serialVersionUID = 1605786424890L;

    private Integer id;

    private HistoryImageGroupModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HistoryImageGroupModel getModel() {
        return model;
    }

    public void setModel(HistoryImageGroupModel model) {
        this.model = model;
    }
}
