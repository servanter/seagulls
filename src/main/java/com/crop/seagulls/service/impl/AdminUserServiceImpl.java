package com.crop.seagulls.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.AdminUserDAO;
import com.crop.seagulls.entities.admin.User;
import com.crop.seagulls.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDAO adminUserDAO;

    @Override
    public Response login(User user) {
        User u = adminUserDAO.getByUser(user);
        return u != null ? new Response(ReturnCode.SUCCESS, u) : new Response(ReturnCode.USER_OR_PASSWORD_UNVALID);
    }

}
