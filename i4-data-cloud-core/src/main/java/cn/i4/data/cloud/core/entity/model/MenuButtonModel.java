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
* @date 2020-10-11 13:42:25
*/
@TableName("t_menu_button")
public class MenuButtonModel extends BaseModel<MenuButtonModel> {

    private static final long serialVersionUID = 1602394945631L;
    /**
    * 
    */
    @TableField("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
    * 上级id
    */
    @TableField("parent_id")
    private Integer parentId;

    /**
    * 
    */
    @TableField("name")
    private String name;

    /**
    * 
    */
    @TableField("icon")
    private String icon;

    /**
    * 
    */
    @TableField("url")
    private String url;

    /**
    * 权限标识
    */
    @TableField("permission")
    private String permission;

    /**
    * 排序
    */
    @TableField("sort")
    private Integer sort;

    /**
    * 0菜单，1按钮
    */
    @TableField("type")
    private Integer type;

    /**
     * 状态，0冻结，1启用
     */
    @TableField("status")
    private Integer status;

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


    public void setId(Integer id) {
    this.id = id;
    }

    public Integer getId() {
    return this.id;
    }

    public void setParentId(Integer parentId) {
    this.parentId = parentId;
    }

    public Integer getParentId() {
    return this.parentId;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getName() {
    return this.name;
    }

    public void setIcon(String icon) {
    this.icon = icon;
    }

    public String getIcon() {
    return this.icon;
    }

    public void setUrl(String url) {
    this.url = url;
    }

    public String getUrl() {
    return this.url;
    }

    public void setPermission(String permission) {
    this.permission = permission;
    }

    public String getPermission() {
    return this.permission;
    }

    public void setSort(Integer sort) {
    this.sort = sort;
    }

    public Integer getSort() {
    return this.sort;
    }

    public void setType(Integer type) {
    this.type = type;
    }

    public Integer getType() {
    return this.type;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
