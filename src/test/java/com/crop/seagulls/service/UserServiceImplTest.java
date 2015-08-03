package com.crop.seagulls.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.User;

public class UserServiceImplTest extends SuperTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindUserById() {
        fail("Not yet implemented");
    }

    @Test
    public void testLogin() {
        User user = new User();
        user.setPhone("15900000000");
        user.setPassword("12345");
        Response response = userService.login(user);
        System.out.println(response.getReturnCode());
    }

    @Test
    public void testRegister() {
        User user = new User();
        user.setPhone("15900000000");
        user.setPassword("12345");
        Response response = userService.register(user);
        System.out.println(response.getReturnCode());
    }

    @Test
    public void testIsNameValid() {
        fail("Not yet implemented");
    }

    @Test
    public void testCompleteInfo() {
        fail("Not yet implemented");
    }

    @Test
    public void testSeacherByUserName() {
        fail("Not yet implemented");
    }

    @Test
    public void testFindUsersByIdsLongArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testFindUsersByIdsListOfLong() {
        fail("Not yet implemented");
    }

}
