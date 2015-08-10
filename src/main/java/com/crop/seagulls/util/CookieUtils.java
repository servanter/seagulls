package com.crop.seagulls.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie utils
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class CookieUtils {

    public static void add(HttpServletResponse response, String name, String valus) {
        Cookie cookie = new Cookie(name, valus);
        cookie.setMaxAge(1000 * 60 * 5);
        response.addCookie(cookie);
    }

    public static String get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
