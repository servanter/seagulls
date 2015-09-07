package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.entities.admin.Menu;

public interface MenuService {

    public List<Menu> findByRoles(List<Long> roleIds);
    
    public List<Menu> findAll();
}
