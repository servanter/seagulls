package com.crop.seagulls.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.service.UserService;

/**
 * 用户控制层
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String enterLogin(Model model) {
        return "user/login";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(User user, HttpServletResponse response) {
        Response result = userService.login(user);
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String enterRegister(Model model) {
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(User user, HttpSession session) {
        Response result = userService.register(user);
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            session.setAttribute("user", user);
        }
        return result;
    }
}
