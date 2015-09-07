package com.crop.seagulls.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static UserDetails getLoginedPrincipal() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof String) {
            return null;
        } else {
            return (UserDetails) object;
        }
    }
}
