package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.CommonStatus;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.UserDAO;
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.CompanyService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.UserAuthService;
import com.crop.seagulls.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public Response login(User user) {
        User u = userDAO.getUserByNameAndPass(user);
        Response response = new Response();
        response.setReturnCode(u != null ? ReturnCode.SUCCESS : ReturnCode.USER_OR_PASSWORD_UNVALID);
        response.setResult(u);
        return response;
    }

    @Override
    public Response register(User user) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.ERROR);
        if (isNameValid(user.getPhone())) {
            userDAO.save(user);
            if (user != null && user.getId() > 0) {
                User updateUser = new User();
                updateUser.setId(user.getId());
                updateUser.setNickName(templateService.getMessage("user.default.nickname", String.valueOf(user.getId())));
                user.setNickName(updateUser.getNickName());
                int affect = userDAO.update(updateUser);
                response.setReturnCode(affect > 0 ? ReturnCode.SUCCESS : ReturnCode.ERROR);
            }
        } else {
            response.setReturnCode(ReturnCode.USER_NAME_READY_REGISTER);
        }
        return response;
    }

    public boolean isNameValid(String userName) {
        return userDAO.isExistUser(userName) <= 0;
    }

    @Override
    public Response completeInfo(User user) {
        Response result = new Response(ReturnCode.SUCCESS);
        if (StringUtils.isNotBlank(user.getRealName())) {

            // need update realname
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(user.getId());
            userAuth.setStatus(CommonStatus.UN_SUBMIT.getCode());
            userAuth.setRealName(user.getRealName());
            if (!ObjectUtils.equals(user.getUserAuthId(), null) && user.getUserAuthId() > 0) {
                userAuth.setId(user.getUserAuthId());
                result = userAuthService.modify(userAuth);
            } else {
                result = userAuthService.add(userAuth);
            }
        }
        if (StringUtils.isNotBlank(user.getCompanyTitle())) {

            // need update company
            if (!ObjectUtils.equals(user.getCompanyId(), null) && user.getCompanyId() > 0) {
                Company company = new Company(user.getCompanyId());
                company.setTitle(user.getCompanyTitle());
                result = companyService.modify(company);
            } else {

                // add the company
                Company company = new Company();
                company.setTitle(user.getCompanyTitle());
                company.setUserId(user.getId());
                result = companyService.add(company);
            }
        }
        if (ReturnCode.isSuccess(result.getReturnCode())) {
            result = userDAO.update(user) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        }
        return result;
    }

    @Override
    public List<User> seacherByUserName(String userNick) {
        return userDAO.getUserByNick(userNick);
    }

    @Override
    public List<User> findUsersByIds(Long[] ids) {
        if (ids == null || ids.length == 0) {
            return null;
        }
        return userDAO.getUsersByIds(ids);
    }

    @Override
    public List<User> findUsersByIds(List<Long> ids) {
        if (ids != null && ids.size() > 0) {
            Long[] arr = new Long[ids.size()];
            ids.toArray(arr);
            return findUsersByIds(arr);
        }
        return new ArrayList<User>();
    }

    @Override
    public Response checkPhone(String phone) {
        Response response = new Response();
        if (isNameValid(phone)) {
            response.setReturnCode(ReturnCode.SUCCESS);
        } else {
            response.setReturnCode(ReturnCode.USER_NAME_READY_REGISTER);
        }
        return response;
    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.getByUserName(userName);
    }

    @Override
    public Map<String, Object> userProfile(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = findUserById(id);
        map.put("model", user);
        return map;
    }

    @Override
    public Map<String, Object> findUserByIdWithCompany(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        User model = userDAO.getUserById(user.getId());

        // company infos
        Paging<Company> companies = companyService.findByUserId(user);
        if (companies != null && CollectionUtils.isNotEmpty(companies.getResult())) {
            map.put("company", companies.getResult().get(0));
        }

        // user auth infos
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(user.getId());
        map.put("userAuth", userAuthService.findByUserId(user.getId()));
        map.put("model", model);
        map.put("commonStatus", CommonStatus.getMap());
        return map;
    }

    @Override
    public List<User> findFriendUsers(Long userId) {
        return userDAO.getFriendUsers(userId);
    }

    @Override
    public Response forgetPassword(User user) {
        return userDAO.updatePassword(user) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response hasPhone(String phone) {
        Response response = new Response();
        if (isNameValid(phone)) {
            response.setReturnCode(ReturnCode.USER_NOT_FOUND);
        } else {
            response.setReturnCode(ReturnCode.SUCCESS);
        }
        return response;
    }

    @Override
    public Response modifyPassword(User user, String passwordNew) {
        User u = userDAO.getUserByNameAndPass(user);
        Response response = new Response(u != null ? ReturnCode.SUCCESS : ReturnCode.USER_OR_PASSWORD_UNVALID);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            user.setPassword(passwordNew);
            response = userDAO.update(user) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        }
        return response;
    }
}
