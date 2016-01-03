package com.crop.seagulls.bean;

public enum PersonRejectEnum {

    ID_CARD_UNVALID(1, "身份证、姓名错误"), ID_CARD_PHOTO_UNVALIDA(2, "照片错误");

    private int code;

    private String description;

    private PersonRejectEnum(int code, String description) {
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

    public static PersonRejectEnum code2Rejection(int code) {
        PersonRejectEnum[] values = PersonRejectEnum.values();
        for (PersonRejectEnum every : values) {
            if (every.getCode() == code) {
                return every;
            }
        }
        return null;
    }

}
