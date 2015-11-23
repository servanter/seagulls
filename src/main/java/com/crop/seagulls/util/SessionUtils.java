package com.crop.seagulls.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import com.crop.seagulls.common.Constant;
import com.crop.seagulls.entities.User;

public class SessionUtils {

    public static boolean equals(HttpSession session, String key, Object expect) {
        Object object = session.getAttribute(key);
        if (object == null) {
            return false;
        }
        return object.toString().equals(expect);
    }

    public static Object getValue(HttpSession session, String key) {
        return session.getAttribute(key);
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

    public static User getCurUser(HttpSession session) {
        User u = (User) getValue(session, Constant.LOGIN_USER);
        if(u != null) {
            return u;
        }
        return new User(-1L);
    }

    public static void mapSet(HttpSession session, Map<String, Object> map) {
        for (Entry<String, Object> entry : map.entrySet()) {
            setValue(session, entry.getKey(), entry.getValue());
        }
    }
}
