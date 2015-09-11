package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.MenuDAO;
import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Override
    public List<Menu> findByRoles(List<Long> roleIds) {
        return menuDAO.findByRoles(roleIds);
    }

    @Override
    public List<Menu> findAll() {
        return menuDAO.findAllMenusWithRoles();
    }

}
