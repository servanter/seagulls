package com.crop.seagulls.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    public static String removeEndZero(String text) {
        Pattern pattern = Pattern.compile(".0*$");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String replace = matcher.group();
            return text.replace(replace, "");
        }
        return text;
    }
}
