package com.crop.seagulls.bean;

public enum SellRejectEnum {

    TITLE_ERROR(1, "标题错误"),

    IMAGE_ERROR(2, "上传图片错误");

    private int code;

    private String description;

    private SellRejectEnum(int code, String description) {
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

    public static SellRejectEnum code2Rejection(int code) {
        SellRejectEnum[] values = SellRejectEnum.values();
        for (SellRejectEnum every : values) {
            if (every.getCode() == code) {
                return every;
            }
        }
        return null;
    }
}
