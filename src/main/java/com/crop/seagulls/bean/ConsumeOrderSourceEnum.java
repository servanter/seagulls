package com.crop.seagulls.bean;

public enum ConsumeOrderSourceEnum {

    SELL_PRODUCT(1, "供货商品"), GROUP_PURCHASE(2, "团购");

    private int code;

    private String description;

    private ConsumeOrderSourceEnum(int code, String description) {
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

    public static ConsumeOrderSourceEnum code2Source(int code) {
        ConsumeOrderSourceEnum[] types = ConsumeOrderSourceEnum.values();
        for (ConsumeOrderSourceEnum type : types) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return ConsumeOrderSourceEnum.SELL_PRODUCT;
    }

}
