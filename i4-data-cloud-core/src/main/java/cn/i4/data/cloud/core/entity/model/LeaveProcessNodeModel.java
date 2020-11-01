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
* @date 2020-11-01 14:58:27
*/
@TableName("t_leave_process_node")
public class LeaveProcessNodeModel extends BaseModel<LeaveProcessNodeModel> {

    private static final long serialVersionUID = 1604213907770L;
    /**
    * 
    */
    @TableField("id")
    private Integer id;

    /**
    * 请假流程id
    */
    @TableField("leave_process_id")
    private Integer leaveProcessId;

    /**
    * 
    */
    @TableField("upper_id")
    private Integer upperId;

    /**
    * 
    */
    @TableField("node_name")
    private String nodeName;

    /**
    * 当前节点的创建人
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 
    */
    @TableField("receive_user_id")
    private Integer receiveUserId;

    /**
    * 办理时间
    */
    @TableField("deal_time")
    private Long dealTime;

    /**
    * 办理类型，0拒绝，1放行，2驳回
    */
    @TableField("deal_type")
    private Integer dealType;

    /**
    * 备注解释
    */
    @TableField("comment")
    private String comment;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setLeaveProcessId(Integer leaveProcessId) {
    this.leaveProcessId = leaveProcessId;
    }

    public Integer getLeaveProcessId() {
    return this.leaveProcessId;
    }

    public void setUpperId(Integer upperId) {
    this.upperId = upperId;
    }

    public Integer getUpperId() {
    return this.upperId;
    }

    public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
    }

    public String getNodeName() {
    return this.nodeName;
    }

    public void setUserId(Integer userId) {
    this.userId = userId;
    }

    public Integer getUserId() {
    return this.userId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
    this.receiveUserId = receiveUserId;
    }

    public Integer getReceiveUserId() {
    return this.receiveUserId;
    }

    public void setDealTime(Long dealTime) {
    this.dealTime = dealTime;
    }

    public Long getDealTime() {
    return this.dealTime;
    }

    public void setDealType(Integer dealType) {
    this.dealType = dealType;
    }

    public Integer getDealType() {
    return this.dealType;
    }

    public void setComment(String comment) {
    this.comment = comment;
    }

    public String getComment() {
    return this.comment;
    }

}
