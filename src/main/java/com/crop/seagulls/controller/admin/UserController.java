package com.crop.seagulls.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.common.Constant;
import com.crop.seagulls.entities.admin.User;
import com.crop.seagulls.service.AdminUserService;
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
        if (SessionUtils.notNull(session, Constant.LOGIN_ADMIN_USER)) {
            return "redirect:/admin/user/home";
        } else {
            if (redirect == null) {
                redirect = "/";
            }
            model.addAttribute("redirectUrl", redirect);
            return "admin/user/login";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public Response login(User user,HttpSession session) {
        Response result = adminUserService.login(user);
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            SessionUtils.setValue(session, Constant.LOGIN_ADMIN_USER, result.getResult());
            result.setMessage("admin/user/home/");
        }
        return result;
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
        
        return "admin/user/home";
    }
}
