package cn.i4.data.cloud.autocode.entity;

/**
 * 字段的dto
 * @author wangjc
 * @title: IntelliJ IDEA
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/27-17:38
 */
public class FieldDto {

    private String fieldName;

    private String fieldComment;

    private CloumnEnums filed;

    private String javaType;

    public String getJavaType() {
        return this.filed.getJavaType();
    }


    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    public CloumnEnums getFiled() {
        return filed;
    }

    public void setFiled(CloumnEnums filed) {
        this.filed = filed;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
