package com.crop.seagulls.bean;

public enum PayTypeEnum {

    DEFAULT(-999, ""), WEIXIN(1, "微信支付");

    private int code;

    private String description;

    private PayTypeEnum(int code, String description) {
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

    public static PayTypeEnum code2PayType(int code) {
        PayTypeEnum[] types = PayTypeEnum.values();
        for (PayTypeEnum type : types) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return PayTypeEnum.DEFAULT;
    }

}
