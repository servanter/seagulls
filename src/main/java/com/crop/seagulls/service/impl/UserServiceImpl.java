package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.spi.ErrorCode;
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
        response.setReturnCode(u != null ? ReturnCode.SUCCESS : ReturnCode.USER_NOT_FOUNT);
        return response;
    }

    @Override
    public Long register(User user) {
        if (isNameValid(user.getUserName())) {
            userDAO.save(user);
            return user.getId();
        }

        // exists this username
        return null;
    }

    @Override
    public boolean isNameValid(String userName) {
        List<User> result = userDAO.isExistUser(userName);
        return (result != null && result.size() > 0) ? false : true;
    }

    @Override
    public boolean logout(Long id) {
        User user = new User(id);
        user.setIsValid(false);
        return userDAO.modifyIsValid(user) > 0;
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
    public List<User> findUsersByPaging(User user) {
        return userDAO.getUsers(new HashMap<String, Object>());
    }

    @Override
    public List<User> findUserNames() {
        return userDAO.getUserNameAndId();
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
}
