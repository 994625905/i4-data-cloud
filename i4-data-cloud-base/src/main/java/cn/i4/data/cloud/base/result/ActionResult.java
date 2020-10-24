package cn.i4.data.cloud.base.result;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * action的结果返回集
 * @author wangjc
 * @title: ActionResult
 * @projectName i4-data-cloud
 * @description: TODO
 * @date 2020/9/2919:17
 */
public class ActionResult<T> implements Serializable {

    private static final long serialVersionUID = 7509372121759188693L;

    private int code;

    private String message;

    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    ActionResult() {
        this(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }

    public ActionResult(int code, String message) {
        this(code, message, null);
    }

    public ActionResult(int code, String message, T result) {
        super();
        this.setCode(code);
        this.setMessage(message);
        this.setResult(result);
    }

    private ActionResult<T> code(int code) {
        this.setCode(code);
        return this;
    }

    private ActionResult<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public ActionResult<T> result(T result) {
        this.setResult(result);
        return this;
    }

    @JsonIgnore
    public boolean success() {
        return ResultEnum.SUCCESS.getCode() == this.code;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static <T> ActionResult<T> ok(T t){
        ActionResult<T> actionResult = new ActionResult<T>();
        actionResult.setCode(ResultEnum.SUCCESS.getCode());
        actionResult.setMessage(ResultEnum.SUCCESS.getMsgCn());
        actionResult.setResult(t);
        return actionResult;
    }

    public static <T> ActionResult<T> error(){
        ActionResult<T> actionResult = new ActionResult<T>();
        actionResult.setCode(ResultEnum.EXCEPTION.getCode());
        actionResult.setMessage(ResultEnum.EXCEPTION.getMsgCn());
        return actionResult;
    }

    public static <T> ActionResult<T> error(String msg){
        ActionResult<T> actionResult = new ActionResult<T>();
        actionResult.setCode(ResultEnum.EXCEPTION.getCode());
        actionResult.setMessage(msg);
        return actionResult;
    }

    public static <T> ActionResult<T> error(Integer code,String msg){
        ActionResult<T> actionResult = new ActionResult<T>();
        actionResult.setCode(code);
        actionResult.setMessage(msg);
        return actionResult;
    }

}
