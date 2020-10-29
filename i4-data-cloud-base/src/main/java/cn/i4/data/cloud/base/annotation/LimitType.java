package cn.i4.data.cloud.base.annotation;

/**
 * @author wangjc
 * @title: LimitType
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/10/28-19:15
 */
public enum LimitType {

    IP(428,"IP current limiting"),//针对IP限制
    USER(429,"user current limiting"),//针对用户限制
    NORMAL(430,"normal current limiting");//普通限制，纯粹限制当前接口的访问次数，method-->针对所有用户和IP，

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String message;

    private LimitType(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
