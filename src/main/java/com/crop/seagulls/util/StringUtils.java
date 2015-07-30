package com.crop.seagulls.util;

public class StringUtils {

    /**
     * 全角转半角
     * 
     * @param str
     * @return
     */
    public static String toHalfString(String str) {
        return str.replace("，", ",");
    }

    /**
     * 指定的位置转换成大写<br>
     * 例如:<br>
     * index=1 str=abcde <br>
     * return aBcde
     * 
     * @param index
     * @param str
     * @return
     */
    public static String signUpChar(int index, String str) {
        str = str.toLowerCase();
        if (str == null || str.length() == 0) {
            return "";
        }
        if (str.length() - 1 > index) {
            String result = "";
            String signIndexStr = String.valueOf((str.charAt(index))).toUpperCase();
            if (index >= 0) {
                result += str.substring(0, index);
            }
            result += signIndexStr;
            result += str.substring(index + 1, str.length());
            return result;
        } else {
            return str.toUpperCase();
        }
    }
    
    public static boolean isNullOrEmpty(String text) {
        return (text == null || text.length() == 0) ? true : false;
    }
}
