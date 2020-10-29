package cn.i4.data.cloud.autocode.entity;

import java.util.List;

/**
 * table参数的dto
 * @author wangjc
 * @title: TableDto
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/27-17:36
 */
public class TableDto {

    private static final long serialVersionUID = -5505686808979964428L;

    private String tableName;

    private String tableComment;

    private List<FieldDto> fields;

    public List<FieldDto> getFields() {
        return fields;
    }

    public void setFields(List<FieldDto> fields) {
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getBuzName(){
        String tableName = this.getTableName();
        String[] arr = tableName.split("_");
        String buzName = "";
        for(String str : arr){
            if("t".equals(str)){
                continue;
            }
            buzName = buzName + str.substring(0,1).toUpperCase() + str.substring(1);
        }
        return buzName;
    }

    public String getPoName(){
        return this.getTableName();
    }

    public String getVoName(){
        return getBuzName() + "Vo";
    }

    public String getMapperName(){
        return getBuzName() + "Mapper";
    }

    public String getIDaoName(){
        return "I" + getBuzName() + "Dao";
    }

    public String getDaoImplName(){
        return getBuzName() + "DaoImpl";
    }

    public String getIServiceName(){
        return "I" + getBuzName() + "Service";
    }

    public String getServiceImplName(){
        return getBuzName() + "ServiceImpl";
    }

    @Override
    public String toString() {
        return "TableDto{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", fields=" + fields +
                '}';
    }
}
