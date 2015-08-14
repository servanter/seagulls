package com.crop.seagulls.util;

public class DateType {

    private int type;

    private long moreThan;

    public DateType(int type, long moreThan) {
        this.type = type;
        this.moreThan = moreThan;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getMoreThan() {
        return moreThan;
    }

    public void setMoreThan(long moreThan) {
        this.moreThan = moreThan;
    }

}