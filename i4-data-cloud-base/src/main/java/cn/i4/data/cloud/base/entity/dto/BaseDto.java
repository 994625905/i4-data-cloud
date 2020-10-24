package cn.i4.data.cloud.base.entity.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;

/**
 * 请求参数接收bean的基类
 * @author wangjc
 * @title: BaseDto
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:22
 */
public class BaseDto<T> extends Page<T> implements Serializable {

    private static final long serialVersionUID = -1839484485251700852L;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 字段，列名
     */
    private String field;

    /**
     * 排序方式
     */
    private String sort;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名称--realName
     */
    private String realName;

    /**
     * 导出excel的参数，标题，sheet，文件名，后缀
     */
    private String excelTitle;
    private String excelSheetName;
    private String excelFileName;
    private String excelSuffix;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getExcelTitle() {
        return excelTitle;
    }

    public void setExcelTitle(String excelTitle) {
        this.excelTitle = excelTitle;
    }

    public String getExcelSheetName() {
        return excelSheetName;
    }

    public void setExcelSheetName(String excelSheetName) {
        this.excelSheetName = excelSheetName;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public String getExcelSuffix() {
        return excelSuffix;
    }

    public void setExcelSuffix(String excelSuffix) {
        this.excelSuffix = excelSuffix;
    }
}
