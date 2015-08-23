package com.crop.seagulls.controller;

import java.util.Map;
import java.util.Map.Entry;

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
import com.crop.seagulls.entities.User;
import com.crop.seagulls.service.UserService;
import com.crop.seagulls.util.SessionUtils;

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
    public String enterLogin(@RequestParam(value = "redirectUrl", required = false)
    String redirect, HttpSession session, Model model) {
        if (SessionUtils.isLogin(session)) {
            return "redirect:/";
        } else {
            if (redirect == null) {
                redirect = "/";
            }
            model.addAttribute("redirectUrl", redirect);
            return "user/login";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(User user, @RequestParam(value = "redirectUrl", required = false)
    String redirect, HttpSession session) {
        Response result = userService.login(user);
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            SessionUtils.setValue(session, Constant.LOGIN_USER, result.getResult());
            result.setResult(redirect);
        }
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session, Model model) {
        if (SessionUtils.isLogin(session)) {
            SessionUtils.setValue(session, Constant.LOGIN_USER, null);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String enterRegister(HttpSession session) {
        if (SessionUtils.isLogin(session)) {
            return "redirect:/";
        } else {
            return "user/register";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(User user, HttpSession session) {
        Response result = userService.register(user);
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            SessionUtils.setValue(session, Constant.LOGIN_USER, user);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/checkPhone", method = RequestMethod.GET)
    public Response checkPhone(@RequestParam("phone")
    String phone) {
        Response response = userService.checkPhone(phone);
        return response;
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String profile(HttpSession session, Model model) {
        Map<String, Object> map = userService.userProfile(SessionUtils.getCurUser(session).getId());
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "user/profile";
    }

    @ResponseBody
    @RequestMapping(value = "/user/profile", method = RequestMethod.POST)
    public Response modifyProfile(HttpSession session, User user) {
        user.setId(SessionUtils.getCurUser(session).getId());
        Response response = userService.completeInfo(user);
        return response;
    }
}
