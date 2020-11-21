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
@TableName("t_history_notice_read")
public class HistoryNoticeReadModel extends BaseModel<HistoryNoticeReadModel> {

    private static final long serialVersionUID = 1605939064223L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 公告id
    */
    @TableField("notice_id")
    private Integer noticeId;

    /**
    * 
    */
    @TableField("create_user_id")
    private Integer createUserId;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setNoticeId(Integer noticeId) {
    this.noticeId = noticeId;
    }

    public Integer getNoticeId() {
    return this.noticeId;
    }

    public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
    }

    public Integer getCreateUserId() {
    return this.createUserId;
    }

}
