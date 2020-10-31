package cn.i4.data.cloud.core.entity.view;

import cn.i4.data.cloud.core.entity.model.UserModel;
import cn.i4.data.cloud.base.entity.view.BaseView;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * View
 * @author wangjc
 * @date 2020-10-10 10:14:11
 */
public class UserView extends BaseView<UserView> {

	private static final long serialVersionUID = 1602296052082L;

	public UserView(UserModel model) {
		this.id = model.getId();
		this.loginname = model.getLoginname();
		this.realname = model.getRealname();
		this.phone = model.getPhone();
		this.email = model.getEmail();
		this.password = model.getPassword();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
		this.status = model.getStatus();
		this.firstLogin = model.getFirstLogin();
	}

	public UserView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
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
	private String createTimeStr;

	/**
	 * 
	 */
	@TableField("update_time")
	private Long updateTime;
	private String updateTimeStr;

	/**
	 * 状态，1实习，2试用，3正正式，4休假，5离职，6其他
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

	/*****************info字段******************/
	/**
	 * 1男，2女，3未知
	 */
	private Integer gender;

	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 个性签名（50个字符以内）
	 */
	private String signature;
	/**
	 * 头像
	 */
	private String headimage;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 详细地址
	 */
	private String detailAddress;
	/**
	 * 创建时间，最后修改时间
	 */
	private String createTimeStrInfo;
	private String updateTimeStrInfo;

	/**
	 * 所属部门
	 */
	private String departmentName;

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getCreateTimeStrInfo() {
		return createTimeStrInfo;
	}

	public void setCreateTimeStrInfo(String createTimeStrInfo) {
		this.createTimeStrInfo = createTimeStrInfo;
	}

	public String getUpdateTimeStrInfo() {
		return updateTimeStrInfo;
	}

	public void setUpdateTimeStrInfo(String updateTimeStrInfo) {
		this.updateTimeStrInfo = updateTimeStrInfo;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
