package com.crop.seagulls.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final long SECOUND = 1000L;
    public static final long MINUTE = 1000 * 60L;
    public static final long HOUR = 1000 * 60 * 60L;
    public static final long DAY = 1000 * 60 * 60 * 24L;
    public static final long MONTH = 1000 * 60 * 60 * 24 * 31L;
    public static final long YEAR = 1000 * 60 * 60 * 24 * 365L;

    public static final String DEFAULT_FORMATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE_PATTERN = "yyyy-MM-dd";

    public static Date str2Date(String pattern, String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(time);
    }

    public static Date str2Date(String time) throws ParseException {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_FORMATE_PATTERN);
        try {
            date = simpleDateFormat.parse(time);
        } catch (Exception e) {
            date = new SimpleDateFormat(DATE_FORMATE_PATTERN).parse(time);
        }
        return date;
    }

    public static String date2Str(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_FORMATE_PATTERN);
        return simpleDateFormat.format(date);
    }

    public static String date2Str(String pattern, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static DateType getTimeDesc(Date date) {
        int type = 0;
        long moreThan = 0;
        long ago = date.getTime();
        long now = System.currentTimeMillis();
        System.out.println(MONTH);
        System.out.println(YEAR);
        if (now - ago < SECOUND) {
            type = 0;
        } else if (((now - ago) > SECOUND) && ((now - ago) <= MINUTE)) {
            type = 0;
            moreThan = (now - ago) / SECOUND;
        } else if (((now - ago) > MINUTE) && ((now - ago) <= HOUR)) {
            type = 1;
            moreThan = (now - ago) / MINUTE;
        } else if (((now - ago) > HOUR) && ((now - ago) <= DAY)) {
            type = 2;
            moreThan = (now - ago) / HOUR;
        } else if (((now - ago) > DAY) && ((now - ago) <= MONTH)) {
            type = 3;
            moreThan = (now - ago) / DAY;
        } else if (((now - ago) > MONTH) && ((now - ago) <= YEAR)) {
            type = 4;
            moreThan = (now - ago) / MONTH;
        } else {
            type = 5;
            moreThan = (now - ago) / YEAR;
        }
        return new DateType(type, moreThan);
    }
}
