package com.crop.seagulls.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;

public class RoleServiceImplTest extends SuperTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testFindByUserId() {
        System.out.println(roleService.findByUserId(1L));
    }
}
