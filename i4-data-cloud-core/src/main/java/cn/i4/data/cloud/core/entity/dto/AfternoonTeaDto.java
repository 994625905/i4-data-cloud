package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaModel;
import cn.i4.data.cloud.core.entity.view.AfternoonTeaView;

/**
* Dto
* @author wangjc
* @date 2020-11-10 15:14:47
*/
public class AfternoonTeaDto extends BaseDto<AfternoonTeaView> {

    private static final long serialVersionUID = 1604992487118L;

    private Integer id;
    private String name;
    private String typeId;
    private String image;
    private String price;
    private AfternoonTeaModel model;

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

    public AfternoonTeaModel getModel() {
        return model;
    }

    public void setModel(AfternoonTeaModel model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
