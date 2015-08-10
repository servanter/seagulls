package com.crop.seagulls.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static final int DAY = 1000 * 60 * 60 * 24;
    
    public static final String DEFAULT_FORMATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE_PATTERN = "yyyy-MM-dd";
    
    
    public static Date str2Date(String pattern, String time) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(time);
    }
    
    public static Date str2Date(String time) throws ParseException{
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
    
    public static String date2Str(String pattern ,Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
    
}
