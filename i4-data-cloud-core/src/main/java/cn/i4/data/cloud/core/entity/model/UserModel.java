package cn.i4.data.cloud.core.entity.model;

import cn.i4.data.cloud.base.entity.model.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
* Model
* @author wangjc
* @date 2020-10-10 10:14:11
*/
@TableName("t_user")
public class UserModel extends BaseModel<UserModel> {

    private static final long serialVersionUID = 1602296051900L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名
     */
    @TableField("loginname")
    private String loginname;

    /**
    * 真实姓名，不超过10位
    */
    @TableField("realname")
    private String realname;

    /**
     * 工号
     */
    @TableField("job_number")
    private String jobNumber;

    /**
    * 
    */
    @TableField("phone")
    private String phone;

    /**
    * 
    */
    @TableField("email")
    private String email;

    /**
    * 
    */
    @TableField("password")
    private String password;

    /**
    * 
    */
    @TableField("create_time")
    private Long createTime;

    /**
    * 
    */
    @TableField("update_time")
    private Long updateTime;

    /**
    * 状态，1实习，2试用，3正式，4休假，5离职，6其他
    */
    @TableField("status")
    private Integer status;

    /**
     * 首次登陆，1首次，2多次
     */
    @TableField("first_login")
    private Integer firstLogin;


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {

    return this.id;
    }
    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public void setRealname(String realname) {
    this.realname = realname;
    }

    public String getRealname() {
    return this.realname;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setPhone(String phone) {
    this.phone = phone;
    }

    public String getPhone() {
    return this.phone;
    }

    public void setEmail(String email) {
    this.email = email;
    }

    public String getEmail() {
    return this.email;
    }

    public void setPassword(String password) {
    this.password = password;
    }

    public String getPassword() {
    return this.password;
    }

    public void setCreateTime(Long createTime) {
    this.createTime = createTime;
    }

    public Long getCreateTime() {
    return this.createTime;
    }

    public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    }

    public Long getUpdateTime() {
    return this.updateTime;
    }

    public void setStatus(Integer status) {
    this.status = status;
    }

    public Integer getStatus() {
    return this.status;
    }

    public Integer getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Integer firstLogin) {
        this.firstLogin = firstLogin;
    }
}
