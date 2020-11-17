package cn.i4.data.cloud.core.entity.dto;

import cn.i4.data.cloud.base.entity.dto.BaseDto;
import cn.i4.data.cloud.core.entity.model.FileModel;
import cn.i4.data.cloud.core.entity.view.FileView;

import java.util.List;

/**
* Dto
* @author wangjc
* @date 2020-10-13 11:39:37
*/
public class FileDto extends BaseDto<FileView> {

    private static final long serialVersionUID = 1602560378545L;

    private Integer id;
    /**
     * 文件类型
     */
    private Integer type;
    /**
     * 限制宽度
     */
    private Integer width;
    /**
     * 限制高度
     */
    private Integer height;
    /**
     * 限制大小
     */
    private Integer fileSize;
    /**
     * 等比例限制0/1
     */
    private String limitProp;

    /**
     * 调用删除的链接
     */
    private String fileUrl;
    /**
     * 状态
     */
    private Integer status;

    private FileModel model;

    /**
     * 文件列表
     */
    private List<FileView> fileList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getLimitProp() {
        return limitProp;
    }

    public void setLimitProp(String limitProp) {
        this.limitProp = limitProp;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public FileModel getModel() {
        return model;
    }

    public void setModel(FileModel model) {
        this.model = model;
    }

    public List<FileView> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileView> fileList) {
        this.fileList = fileList;
    }
}
