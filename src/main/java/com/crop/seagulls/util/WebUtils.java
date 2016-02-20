package com.crop.seagulls.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip.contains(",")) {

            // has multi ips
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

    public static boolean isWeiXinBroswer(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        Pattern pattern = Pattern.compile("MicroMessenger/(\\d{0,}[.]?)*");
        Matcher matcher = pattern.matcher(userAgent);
        String version = "";
        if (matcher.find()) {
            version = matcher.group().replace("MicroMessenger/", "");
            if (version.split("\\.").length > 0 && Integer.parseInt(version.split("\\.")[0]) >= 5) {
                return true;
            }
        }
        return false;
    }
}
