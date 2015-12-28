package com.crop.seagulls.bean;

public enum RejectCategory {

    PERSON(1),

    COMPANY(2);

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private RejectCategory(int ordinal) {
        this.code = ordinal;
    }

}
