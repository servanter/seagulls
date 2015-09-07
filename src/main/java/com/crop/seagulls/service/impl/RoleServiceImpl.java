package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.RoleDAO;
import com.crop.seagulls.entities.admin.Role;
import com.crop.seagulls.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public List<Role> findByUserId(Long userId) {
        return roleDAO.getByUserId(userId);
    }

}
