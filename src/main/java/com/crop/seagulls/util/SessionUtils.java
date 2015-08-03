package com.crop.seagulls.util;

import javax.servlet.http.HttpSession;

import com.crop.seagulls.common.Constant;

public class SessionUtils {

    public static boolean equals(HttpSession session, String key, Object expect) {
        Object object = session.getAttribute(key);
        if (object == null) {
            return false;
        }
        return object.toString().equals(expect);
    }

    public static void setValue(HttpSession session, String key, Object value) {
        session.setAttribute(key, value);
    }

    public static boolean notNull(HttpSession session, String key) {
        Object object = session.getAttribute(key);
        return object != null;
    }

    public static boolean isLogin(HttpSession session) {
        return notNull(session, Constant.LOGIN_USER);
    }
}
