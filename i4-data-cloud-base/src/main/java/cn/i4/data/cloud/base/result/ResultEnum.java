package cn.i4.data.cloud.base.result;

/**
 * action结果集的枚举
 * @author wangjc
 * @title: ResultEnum
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:19
 */
public enum ResultEnum {

    SUCCESS(200,"OK","请求成功"),
    UNAUTHORIZED(401,"un authorized","token验证失败"),
    LOGIN_FAIL_USER(407,"user invalid","用户身份无效"),
    LOGIN_FAIL_CODE(412,"code error","验证码过期/错误"),
    LOGIN_FAIL_PHONE(413,"phone error","手机号码错误"),
    LOGIN_FAIL_PWD(417,"pwd error","账号密码错误"),
    EXCEPTION(500,"exception","系统内部异常");

    private int code;
    private String msg;
    private String msgCn;

    private ResultEnum(int code, String msgCn) {
        this.code = code;
        this.msgCn = msgCn;
    }

    private ResultEnum(int code, String msg, String msgCn) {
        this.code = code;
        this.msg = msg;
        this.msgCn = msgCn;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getMsgCn() {
        return this.msgCn;
    }


}
