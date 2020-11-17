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
* @date 2020-11-14 14:20:33
*/
@TableName("t_party_activity_sign")
public class PartyActivitySignModel extends BaseModel<PartyActivitySignModel> {

    private static final long serialVersionUID = 1605334833885L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 活动id
    */
    @TableField("activity_id")
    private Integer activityId;

    /**
    * 签到参与用户
    */
    @TableField("sign_user_id")
    private Integer signUserId;

    /**
    * 签到参与时间
    */
    @TableField("sign_time")
    private Long signTime;

    /**
    * 签到参与的人员数量，默认本人即为1，携带则累加，不去则为0，所以是必填项
    */
    @TableField("sign_user_count")
    private Integer signUserCount;

    /**
    * 如果携带家属的话，该项为必填项，如果携带家属的话，注明携带成员身份，如果不去的话，说明原因
    */
    @TableField("sign_describe_info")
    private String signDescribeInfo;

    /**
    * 交通方式（仅限集体乘车的traffic_type生效），0跟团，1自驾
    */
    @TableField("sign_traffic")
    private Integer signTraffic;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setActivityId(Integer activityId) {
    this.activityId = activityId;
    }

    public Integer getActivityId() {
    return this.activityId;
    }

    public void setSignUserId(Integer signUserId) {
    this.signUserId = signUserId;
    }

    public Integer getSignUserId() {
    return this.signUserId;
    }

    public void setSignTime(Long signTime) {
    this.signTime = signTime;
    }

    public Long getSignTime() {
    return this.signTime;
    }

    public void setSignUserCount(Integer signUserCount) {
    this.signUserCount = signUserCount;
    }

    public Integer getSignUserCount() {
    return this.signUserCount;
    }

    public void setSignDescribeInfo(String signDescribeInfo) {
    this.signDescribeInfo = signDescribeInfo;
    }

    public String getSignDescribeInfo() {
    return this.signDescribeInfo;
    }

    public void setSignTraffic(Integer signTraffic) {
    this.signTraffic = signTraffic;
    }

    public Integer getSignTraffic() {
    return this.signTraffic;
    }

}
