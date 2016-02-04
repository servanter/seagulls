package com.crop.seagulls.bean;

public enum OrderStatusEnum {

    PAY_NOT(0, "未支付"),

    CANCEL(-1, "已取消"),

    ERROR(-999, "系统错误"),

    PAY_SUCCESS(2, "已支付");
    private int code;

    private String description;

    private OrderStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
