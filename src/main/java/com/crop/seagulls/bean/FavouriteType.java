package com.crop.seagulls.bean;

public enum FavouriteType {

    SELL(1, "sell"),

    BUY(2, "buy");

    private int code;

    private String description;

    private FavouriteType(int code, String desciption) {
        this.code = code;
        this.description = desciption;
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
