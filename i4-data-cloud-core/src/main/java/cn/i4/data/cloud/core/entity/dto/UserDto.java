package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.core.entity.model.UserInfoModel;
import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.core.entity.view.UserView;
import cn.i4.data.cloud.base.entity.dto.BaseDto;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-10-10 10:14:11
*/
public class UserDto extends BaseDto<UserView> {

    private static final long serialVersionUID = 1602296052095L;

    /** 手机，邮箱，验证码，邀请码参数 */
    private Integer code;
    private String codeKey;
    private String phone;
    private String email;
    private String inviteCode;

    /** 账号密码参数 */
    private String password;
    private String loginName;

    private Integer status;
    private UserModel user;
    private UserInfoModel userInfo;
    private List<Integer> roleList;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserInfoModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoModel userInfo) {
        this.userInfo = userInfo;
    }

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }
}
