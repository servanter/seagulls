package com.crop.seagulls.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.crop.seagulls.common.Constant;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.UserService;
import com.crop.seagulls.util.AESUtils;
import com.crop.seagulls.util.CookieUtils;
import com.crop.seagulls.util.DateUtils;

public class LoginFilter implements Filter {

    String[] includeURL = null;

    private UserService userService;
    private TemplateService templateService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        userService = (UserService) webApplicationContext.getBean("userService");
        templateService = (TemplateService) webApplicationContext.getBean("templateService");
        String value = filterConfig.getInitParameter("includeURL");
        value = value.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "").replaceAll(", ", ",");
        if (value.indexOf(",") != -1) {
            includeURL = value.split(",");
        } else {
            includeURL = new String[1];
            includeURL[0] = value;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String contextPath = req.getContextPath();
        String fullURL = req.getRequestURI();
        String targetURL = "";
        if (fullURL.length() > 0 && !fullURL.equals(contextPath)) {
            targetURL = fullURL.substring(contextPath.length() + 1);
        } else {
            filter.doFilter(request, response);
            return;
        }

        if (targetURL.indexOf(".") > 0) {
            String post = targetURL.substring(targetURL.lastIndexOf(".") + 1);
            if (StringUtils.isNotBlank(post) && (post.equals("css") || post.equals("js") || post.equals("jpg") || post.equals("png") || post.equals("ico") || post.equals("gif"))) {
                filter.doFilter(request, response);
                return;
            }
        }

        boolean flag = false;
        boolean needRedirect = false;
        if (StringUtils.isNotBlank(targetURL) && !targetURL.equals("/")) {
            for (int i = 0; i < includeURL.length; i++) {
                if (includeURL[i].contains(targetURL)) {
                    flag = true;
                    break;
                }
                Pattern pattern = Pattern.compile(includeURL[i]);
                if (pattern.matcher(targetURL).find() && !targetURL.contains("admin")) {
                    flag = true;
                    break;
                }
            }
        }

        if (flag) {
            Object object = session.getAttribute(Constant.LOGIN_USER);
            if (object == null) {
                String aes = CookieUtils.get(req, Constant.COOKIE_SESSION_KEY);
                if (StringUtils.isNotBlank(aes)) {
                    String message = AESUtils.Decrypt(aes, "");

                    // username,time
                    if (StringUtils.isNotBlank(message) && message.split(",").length == 2) {
                        String userName = message.split(",")[0];
                        String time = message.split(",")[1];
                        int day = Integer.parseInt(templateService.getMessage("sms.cookie.exipre.time"));
                        if ((System.currentTimeMillis() - (Long.parseLong(time)) / DateUtils.DAY) >= day) {
                            needRedirect = true;
                        } else {
                            User user = userService.findByUserName(userName);
                            if (user == null) {
                                needRedirect = true;
                            } else {
                                session.setAttribute(Constant.LOGIN_USER, user);
                            }
                        }
                    }
                } else {
                    needRedirect = true;
                }
            }
        }

        if (needRedirect) {
            if (targetURL.startsWith("/ajaxAuth") || targetURL.startsWith("ajaxAuth")) {
                resp.sendRedirect(contextPath + "/loginError/");
                return;
            } else {
                resp.sendRedirect(contextPath + "/login/?redirectUrl=" + targetURL);
                return;
            }
        }
        filter.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
