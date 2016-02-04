package com.crop.seagulls.bean;

public enum Src {

    WEIXIN(1, "微信");

    private int type;

    private String description;

    private Src(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
