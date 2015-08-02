package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.UserDAO;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.service.UserService;

@Service
public class UserServiceImpl implements UserService {

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
        return response;
    }

    @Override
    public Response register(User user) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.ERROR);
        if (isNameValid(user.getPhone())) {
            userDAO.save(user);
            response.setReturnCode(user != null && user.getId() > 0 ? ReturnCode.SUCCESS : ReturnCode.USER_NOT_FOUND);
        } else {
            response.setReturnCode(ReturnCode.USER_NAME_READY_REGISTER);
        }
        return response;
    }

    public boolean isNameValid(String userName) {
        return userDAO.isExistUser(userName) <= 0;
    }

    @Override
    public boolean completeInfo(User user) {
        return userDAO.update(user) > 0;
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
        if(isNameValid(phone)) {
            response.setReturnCode(ReturnCode.SUCCESS);
        } else {
            response.setReturnCode(ReturnCode.USER_NAME_READY_REGISTER);
        }
        return response;
    }

}
