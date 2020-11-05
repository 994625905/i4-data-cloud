package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.view.SystemConstantView;

/**
* Dto
* @author wangjc
* @date 2020-10-14 14:43:30
*/
public class SystemConstantDto extends BaseDto<SystemConstantView> {

    private static final long serialVersionUID = 1602657811101L;

    private Integer id;

    private String value;

    private String mongoId;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
