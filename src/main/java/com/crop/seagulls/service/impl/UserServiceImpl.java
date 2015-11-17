package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.UserDAO;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private TemplateService templateService;

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
        return userDAO.update(user) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
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
        map.put("user", user);
        return map;
    }

}
