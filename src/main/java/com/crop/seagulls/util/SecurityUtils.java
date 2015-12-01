package com.crop.seagulls.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.crop.seagulls.entities.admin.User;

public class SecurityUtils {

    public static UserDetails getLoginedPrincipal() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof String) {
            return null;
        } else {
            return (UserDetails) object;
        }
    }

    public static User getLoginedUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof String) {
            return null;
        } else {
            return (User) object;
        }
    }

    public static Long getLoginedUserId() {
        User user = getLoginedUser();
        if (user != null) {
            return user.getId();
        }
        return -1L;
    }
}
