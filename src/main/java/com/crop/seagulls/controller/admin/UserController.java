package com.crop.seagulls.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crop.seagulls.common.Constant;
import com.crop.seagulls.service.AdminUserService;
import com.crop.seagulls.util.SecurityUtils;
import com.crop.seagulls.util.SessionUtils;

/**
 * 用户控制层
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller("adminUserController")
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String enterLogin(@RequestParam(value = "redirectUrl", required = false)
    String redirect, HttpSession session, Model model) {
        UserDetails userDetails = SecurityUtils.getLoginedPrincipal();
        if (userDetails == null) {
            return sendRedirect(redirect, model);
        } else {
            if (userDetails != null) {
                return "redirect:/admin/home/";
            } else {
                return sendRedirect(redirect, model);
            }
        }
    }

    private String sendRedirect(String redirect, Model model) {
        if (redirect == null) {
            redirect = "/";
        }
        model.addAttribute("redirectUrl", redirect);
        return "admin/user/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session, Model model) {
        if (SessionUtils.notNull(session, Constant.LOGIN_ADMIN_USER)) {
            SessionUtils.setValue(session, Constant.LOGIN_ADMIN_USER, null);
        }
        return "redirect:/admin/login/";
    }

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public String home(HttpSession session, Model model) {
        return "admin/home";
    }
}
