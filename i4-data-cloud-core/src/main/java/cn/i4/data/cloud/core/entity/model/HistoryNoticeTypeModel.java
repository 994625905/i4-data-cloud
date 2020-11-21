package cn.i4.data.cloud.core.entity.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.i4.data.cloud.base.entity.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* Model
* @author wangjc
* @date 2020-11-21 14:11:04
*/
@TableName("t_history_notice_type")
public class HistoryNoticeTypeModel extends BaseModel<HistoryNoticeTypeModel> {

    private static final long serialVersionUID = 1605939064242L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 公告名称
    */
    @TableField("name")
    private String name;

    /**
    * 描述信息
    */
    @TableField("describe_info")
    private String describeInfo;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getName() {
    return this.name;
    }

    public void setDescribeInfo(String describeInfo) {
    this.describeInfo = describeInfo;
    }

    public String getDescribeInfo() {
    return this.describeInfo;
    }

}
