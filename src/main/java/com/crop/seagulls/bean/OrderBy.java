package com.crop.seagulls.bean;

public enum OrderBy {

    NEW_PUBLISH(0, "最新发布"),

    PRICE_UP(1, "价格由低到高"),

    PRICE_DOWN(2, "价格由高到低");

    private int code;

    private String desc;

    private OrderBy(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static OrderBy code2OrderBy(int code) {
        OrderBy[] values = OrderBy.values();
        for (OrderBy orderBy : values) {
            if (orderBy.getCode() == code) {
                return orderBy;
            }
        }
        return OrderBy.NEW_PUBLISH;
    }
}
