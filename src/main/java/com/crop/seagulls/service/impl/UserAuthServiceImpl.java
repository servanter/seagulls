package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.UserAuthDAO;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.UserAuthService;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDAO userAuthDAO;

    @Override
    public Response add(UserAuth userAuth) {
        return userAuthDAO.save(userAuth) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Paging<UserAuth> findList(UserAuth userAuth) {
        List<UserAuth> result = userAuthDAO.getList(userAuth);
        int total = userAuthDAO.getListCount(userAuth);
        return new Paging<UserAuth>(total, userAuth.getPage(), userAuth.getPageSize(), result);
    }

}
