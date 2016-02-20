package com.crop.seagulls.bean;

public enum ConsumeOrderPayWayEnum {

    DEFAULT(0, "未选择"), WEIXIN(1, "微信支付");

    private int code;

    private String description;

    private ConsumeOrderPayWayEnum(int code, String description) {
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

    public static ConsumeOrderPayWayEnum code2PayType(int code) {
        ConsumeOrderPayWayEnum[] types = ConsumeOrderPayWayEnum.values();
        for (ConsumeOrderPayWayEnum type : types) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return ConsumeOrderPayWayEnum.DEFAULT;
    }

}
