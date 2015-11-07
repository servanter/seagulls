package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.IdentityDAO;
import com.crop.seagulls.entities.UserIdentity;
import com.crop.seagulls.service.IdentityService;

@Service
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private IdentityDAO identityDAO;

    @Override
    public List<UserIdentity> findList(UserIdentity userIdentity) {
        return identityDAO.getList(userIdentity);
    }

}
