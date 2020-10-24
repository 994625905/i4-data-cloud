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
* @date 2020-10-18 14:34:31
*/
@TableName("t_invite_code")
public class InviteCodeModel extends BaseModel<InviteCodeModel> {

    private static final long serialVersionUID = 1603002871215L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 邀请名称
    */
    @TableField("name")
    private String name;

    /**
    * 邀请码
    */
    @TableField("code")
    private String code;

    /**
     * 用户状态：状态，1实习，2试用，3转正，4休假，5离职，6其他
     */
    @TableField("user_status")
    private Integer userStatus;

    /**
    * 创建用户
    */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
    * 创建时间
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 
    */
    @TableField("over_time")
    private Long overTime;

    /**
    * 有效时间，精确到秒
    */
    @TableField("effect_time")
    private Long effectTime;


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

    public void setCode(String code) {
    this.code = code;
    }

    public String getCode() {
    return this.code;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
    }

    public Integer getCreateUserId() {
    return this.createUserId;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setOverTime(Long overTime) {
    this.overTime = overTime;
    }

    public Long getOverTime() {
    return this.overTime;
    }

    public void setEffectTime(Long effectTime) {
    this.effectTime = effectTime;
    }

    public Long getEffectTime() {
    return this.effectTime;
    }

}
