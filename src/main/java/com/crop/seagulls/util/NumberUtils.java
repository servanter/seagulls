package com.crop.seagulls.util;

import java.util.ArrayList;
import java.util.List;

public class NumberUtils {

    public static boolean isValidateNumber(Number i) {
        if (i != null && i.intValue() > Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }

    public static <T> List<T> strSplit2List(String str, String split, Class<T> clazz) {
        List result = new ArrayList();
        String[] arr = str.split(split);
        for (String a : arr) {
            if (clazz.equals(Integer.class)) {
                result.add(Integer.parseInt(a));
            } else if (clazz.equals(Long.class)) {
                result.add(Long.parseLong(a));
            }
        }
        return result;

    }
}
