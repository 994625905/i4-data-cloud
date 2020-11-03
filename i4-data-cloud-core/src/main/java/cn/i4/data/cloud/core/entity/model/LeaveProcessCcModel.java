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
* @date 2020-11-03 18:13:38
*/
@TableName("t_leave_process_cc")
public class LeaveProcessCcModel extends BaseModel<LeaveProcessCcModel> {

    private static final long serialVersionUID = 1604398418568L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 请假条id
    */
    @TableField("leave_id")
    private Integer leaveId;

    /**
    * 请假流程ID
    */
    @TableField("leave_process_id")
    private Integer leaveProcessId;

    /**
    * 抄送用户id
    */
    @TableField("user_id")
    private Integer userId;

    /**
    * 0未阅读，1已阅读
    */
    @TableField("read")
    private Integer read;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setLeaveId(Integer leaveId) {
    this.leaveId = leaveId;
    }

    public Integer getLeaveId() {
    return this.leaveId;
    }

    public void setLeaveProcessId(Integer leaveProcessId) {
    this.leaveProcessId = leaveProcessId;
    }

    public Integer getLeaveProcessId() {
    return this.leaveProcessId;
    }

    public void setUserId(Integer userId) {
    this.userId = userId;
    }

    public Integer getUserId() {
    return this.userId;
    }

    public void setRead(Integer read) {
    this.read = read;
    }

    public Integer getRead() {
    return this.read;
    }

}
