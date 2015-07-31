package com.crop.seagulls.bean;

/**
 * 错误代码
 * 
 * @author zhanghongyan
 * 
 */
public enum ReturnCode {

    SUCCESS(10000, "成功"),

    USER_NOT_FOUNT(20001, "用户没有找到"),

    USER_NAME_READY_REGISTER(20002, "用户名已被注册"),

    ERROR(99999, "系统错误");

    private int code;

    private String message;

    private ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

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

    public static boolean isSuccess(ReturnCode returnCode) {
        return returnCode.getCode() == ReturnCode.SUCCESS.getCode() ? true : false;
    }

}
