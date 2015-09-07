package com.crop.seagulls.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;
import com.crop.seagulls.entities.admin.Menu;

public class MenuServiceImplTest extends SuperTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testFindByRoles() {
        List<Long> idsList = new ArrayList<Long>();
        idsList.add(1L);
        List<Menu> findByRoles = menuService.findByRoles(idsList);
        System.out.println(findByRoles);
    }
}
