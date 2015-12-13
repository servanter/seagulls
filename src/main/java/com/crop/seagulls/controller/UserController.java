package com.crop.seagulls.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
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
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.CompanyService;
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

    @Autowired
    private CompanyService companyService;

    @ResponseBody
    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public Response loginError() {
        return new Response(ReturnCode.USER_NOT_LOGINED);
    }

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

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public String enterForgetPassword(HttpSession session) {
        return "user/forget_password";
    }

    @ResponseBody
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public Response forgetPassword(@RequestParam("id")
    User user, HttpSession session) {
        return userService.forgetPassword(user);
    }

    @RequestMapping(value = "user/modifyPassword", method = RequestMethod.GET)
    public String modifyPassword(HttpSession session) {
        return "user/modify_password";
    }

    @ResponseBody
    @RequestMapping(value = "user/modifyPassword", method = RequestMethod.POST)
    public Response modifyPassword(Long id, @RequestParam("password")
    String password, @RequestParam("passwordNew")
    String passwordNew, HttpSession session) {
        User user = SessionUtils.getCurUser(session);
        user.setPassword(password);
        Response response =  userService.modifyPassword(user, passwordNew);
        if(ReturnCode.isSuccess(response.getReturnCode())) {
            SessionUtils.setValue(session, Constant.LOGIN_USER, null);
        }
        return response;
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
    public String enterProfileDetail(HttpSession session, Model model) {
        model.mergeAttributes(userService.findUserByIdWithCompany(SessionUtils.getCurUser(session)));
        return "user/profile_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/user/profileDetail", method = RequestMethod.POST)
    public Response profileDetail(User user, HttpServletRequest request) {
        Response result = UploadUtils.upload("images/profile/", "images/profile/", request);
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
        UserAuth userAuth = userAuthService.findByUserId(SessionUtils.getCurUser(request.getSession()).getId());
        model.addAttribute("model", userAuth);
        model.addAttribute("commonStatus", CommonStatus.getMap());
        return "user/personal_certification";
    }

    @ResponseBody
    @RequestMapping(value = "/user/certificationPersonal", method = RequestMethod.POST)
    public Response certificationPersonal(UserAuth userAuth, HttpServletRequest request) {
        userAuth.setUserId(SessionUtils.getCurUser(request.getSession()).getId());
        Response result = UploadUtils.upload("images/auth/", "images/auth/", userAuth, request);
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            userAuth.setStatus(CommonStatus.AUDITING.getCode());
            if (!ObjectUtils.equals(userAuth.getId(), null) && userAuth.getId() > 0) {
                result = userAuthService.modify(userAuth);
            } else {
                result = userAuthService.add(userAuth);
            }
        }
        return result;
    }

    @RequestMapping(value = "/user/certificationOrganization", method = RequestMethod.GET)
    public String enterCertificationOrganization(Model model, HttpServletRequest request) {
        User user = new User();
        user.setId(SessionUtils.getCurUser(request.getSession()).getId());
        Paging<Company> companies = companyService.findByUserId(user);
        if (!ObjectUtils.equals(companies, null) && CollectionUtils.isNotEmpty(companies.getResult())) {
            model.addAttribute("model", companies.getResult().get(0));
        }
        model.addAttribute("commonStatus", CommonStatus.getMap());
        return "user/organization_certification";
    }

    @ResponseBody
    @RequestMapping(value = "/user/certificationOrganization", method = RequestMethod.POST)
    public Response certificationOrganization(Company company, HttpServletRequest request) {
        company.setUserId(SessionUtils.getCurUser(request.getSession()).getId());
        Response result = UploadUtils.upload("images/company/", "images/company/", company, request);
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            company.setStatus(CommonStatus.AUDITING.getCode());
            if (!ObjectUtils.equals(company.getId(), null) && company.getId() > 0) {
                result = companyService.modify(company);
            } else {
                result = companyService.add(company);
            }
        }
        return result;
    }

    @RequestMapping(value = "/user/headBigPic", method = RequestMethod.GET)
    public String headBigPic(@RequestParam("headPic")
    String picUrl, Model model) {
        model.addAttribute("headUrl", picUrl);
        return "user/head_big_pic";
    }

}
