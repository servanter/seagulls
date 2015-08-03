package com.crop.seagulls.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.crop.seagulls.common.Constant;
import com.crop.seagulls.util.SessionUtils;

public class ImageCodeFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        String imageCode = request.getParameter("imageCode");
        if (StringUtils.isNotBlank(imageCode)) {
            boolean isExpect = SessionUtils.equals(request.getSession(), Constant.VERIFICATIONCODE, imageCode);
            if(!isExpect) {
                response.sendRedirect(request.getContextPath() + "/system/imageError/");
                return;
            }
        }
        String smsCode = request.getParameter("smsCode");
        if (StringUtils.isNotBlank(smsCode)) {
            boolean isExpect = SessionUtils.equals(request.getSession(), Constant.SMSCODE, smsCode);
            if(!isExpect) {
                response.sendRedirect(request.getContextPath() + "/system/imageError/");
                return;
            }
        }
        filter.doFilter(arg0, arg1);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
