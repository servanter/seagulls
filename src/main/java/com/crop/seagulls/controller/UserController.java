package com.crop.seagulls.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.CommonStatus;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.common.Constant;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.UserAuthService;
import com.crop.seagulls.service.UserService;
import com.crop.seagulls.util.SessionUtils;
import com.crop.seagulls.util.UploadUtils;

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

    @Autowired
    private UserAuthService userAuthService;

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
            if (StringUtils.isBlank(redirect)) {
                redirect = "/user/profile/";
            }
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
        model.mergeAttributes(map);
        return "user/profile";
    }

    @ResponseBody
    @RequestMapping(value = "/user/profile", method = RequestMethod.POST)
    public Response modifyProfile(HttpSession session, User user) {
        user.setId(SessionUtils.getCurUser(session).getId());
        Response response = userService.completeInfo(user);
        return response;
    }

    @RequestMapping(value = "/user/profileDetail", method = RequestMethod.GET)
    public String enterProfileDetail() {
        return "user/profile_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/user/profileDetail", method = RequestMethod.POST)
    public Response profileDetail(HttpServletRequest request, User user) {
        Response result = UploadUtils.upload("images/profile/", "images/profile", request);
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            if (result.getResult() != null && ((List<String>) result.getResult()).size() > 0) {
                user.setHeadUrl(((List<String>) result.getResult()).get(0));
            }
            result = userService.completeInfo(user);
        }
        return result;
    }

    @RequestMapping(value = "/user/certificationPersonal", method = RequestMethod.GET)
    public String enterCertificationPersonal(Model model, HttpServletRequest request) {
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(SessionUtils.getCurUser(request.getSession()).getId());
        Paging<UserAuth> list = userAuthService.findList(userAuth);
        if (list != null && CollectionUtils.isNotEmpty(list.getResult())) {
            model.addAttribute("model", list.getResult().get(0));
        }
        model.addAttribute("commonStatus", CommonStatus.getMap());
        return "user/personal_certification";
    }

    @ResponseBody
    @RequestMapping(value = "/user/certificationPersonal", method = RequestMethod.POST)
    public Response certificationPersonal(UserAuth userAuth, HttpServletRequest request) {
        userAuth.setUserId(SessionUtils.getCurUser(request.getSession()).getId());
        Response result = UploadUtils.upload("images/auth/", "images/auth/", request);
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            userAuth.setImgFront(((List<String>) result.getResult()).get(0));
            userAuth.setImgBackground(((List<String>) result.getResult()).get(1));
            userAuth.setImgPerson(((List<String>) result.getResult()).get(2));
            result = userAuthService.add(userAuth);
        }
        return result;
    }

    @RequestMapping(value = "/user/certificationOrganization", method = RequestMethod.GET)
    public String enterCertificationOrganization() {
        return "user/organization_certification";
    }

    @ResponseBody
    @RequestMapping(value = "/user/certificationOrganization", method = RequestMethod.POST)
    public Response certificationOrganization() {
        return null;
    }
}
