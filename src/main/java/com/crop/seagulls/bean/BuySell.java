package com.crop.seagulls.bean;

public enum BuySell {

    BUY(1, "BUY"),

    SELL(2, "SELL");

    private int code;

    private String description;

    private BuySell(int code, String description) {
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
    @Override
    public String toString() {
        return super.toString();
    }
    
}
