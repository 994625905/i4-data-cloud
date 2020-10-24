package cn.i4.data.cloud.base.annotation;

/**
 * 请求枚举
 * @author wangjc
 * @title: RequestType
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:36
 */
public enum RequestType {

    INSERT(1,"添加"),
    DELETE(2,"删除"),
    UPDATE(3,"修改"),
    SELECT(4,"查看");

    private Integer type;
    private String name;

    private RequestType(Integer type,String name){
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
