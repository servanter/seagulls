package com.crop.seagulls.util;

public class NumberUtils {

    public static boolean isValidateNumber(Number i) {
        if (i != null && i.intValue() > Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }
}
