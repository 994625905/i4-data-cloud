package cn.i4.data.cloud.core.entity.view;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.i4.data.cloud.base.entity.view.BaseView;
import cn.i4.data.cloud.core.entity.model.AfternoonTeaModel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * View
 * @author wangjc
 * @date 2020-11-10 15:14:47
 */
public class AfternoonTeaView extends BaseView<AfternoonTeaView> {

	private static final long serialVersionUID = 1604992487108L;

	public AfternoonTeaView(AfternoonTeaModel model) {
		this.id = model.getId();
		this.name = model.getName();
		this.image = model.getImage();
		this.price = model.getPrice();
		this.storeAddress = model.getStoreAddress();
		this.createTime = model.getCreateTime();
		this.updateTime = model.getUpdateTime();
		this.createUserId = model.getCreateUserId();
	}

	public AfternoonTeaView() {
		
	}
	
	/**
	 * 
	 */
	@TableField("id")
	private Integer id;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 图片链接
	 */
	@TableField("image")
	private String image;

	/**
	 * 单价
	 */
	@TableField("price")
	private java.math.BigDecimal price;

	/**
	 * 商店地址（简称也行）
	 */
	@TableField("store_address")
	private String storeAddress;

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
	 * 创建者
	 */
	@TableField("create_user_id")
	private Integer createUserId;

	private String createTimeStr;
	private String updateTimeStr;
	private String realName;
	private String selectCount;

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

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public void setPrice(java.math.BigDecimal price) {
		this.price = price;
	}

	public java.math.BigDecimal getPrice() {
		return this.price;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStoreAddress() {
		return this.storeAddress;
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

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
	}

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(String selectCount) {
		this.selectCount = selectCount;
	}
}
