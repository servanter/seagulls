package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.entities.admin.Role;

public interface RoleService {

    public List<Role> findByUserId(Long userId);
    
}
