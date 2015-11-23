package com.crop.seagulls.bean;

public enum SellBuy {

    SELL(1, "SELL"),
    
    BUY(2, "BUY");

    private int code;

    private String description;

    private SellBuy(int code, String description) {
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
