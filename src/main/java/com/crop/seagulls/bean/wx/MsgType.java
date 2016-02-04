package com.crop.seagulls.bean.wx;

public enum MsgType {

    TEXT(1),

    IMAGES(2),

    VIOCE(3),

    VIDEO(4),

    SHORTVIDEO(5),

    LOCATION(6),

    LINK(7),

    EVENT(8);

    private int code;

    private MsgType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
