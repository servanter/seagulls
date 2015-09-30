package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.AdminUserDAO;
import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.entities.admin.User;
import com.crop.seagulls.service.AdminUserService;
import com.crop.seagulls.service.MenuService;
import com.crop.seagulls.service.RoleService;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService, UserDetailsService {

    @Autowired
    private AdminUserDAO adminUserDAO;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Override
    public Response login(User user) {
        User u = adminUserDAO.getByUser(user);
        return u != null ? new Response(ReturnCode.SUCCESS, u) : new Response(ReturnCode.USER_OR_PASSWORD_UNVALID);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = adminUserDAO.getByUserName(username);
        if (StringUtils.isNotBlank(user.getRoleIds())) {
            refreshUserMenus(user);
        }
        return user;
    }

    @Override
    public void refreshUserMenus(User user) {
        List<Long> roleIds = com.crop.seagulls.util.StringUtils.string2Long(user.getRoleIds(), ",");
        List<Menu> menus = menuService.findByRoles(roleIds);
        user.setMenus(menus);

        Map<Long, List<Menu>> menuMap = new HashMap<Long, List<Menu>>();
        for (Menu menu : menus) {
            if (menuMap.containsKey(menu.getParentId())) {
                menuMap.get(menu.getParentId()).add(menu);
            } else {
                List<Menu> curMenus = new ArrayList<Menu>();
                curMenus.add(menu);
                menuMap.put(menu.getParentId(), curMenus);
            }
        }
        user.setMenuMap(menuMap);
    }

    @Override
    public Response add(User user) {
        Paging<User> users = findList(user);
        if (CollectionUtils.isNotEmpty(users.getResult())) {
            return new Response(ReturnCode.USER_NAME_READY_REGISTER);
        }
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        user.setPassword(encoder.encodePassword(user.getPassword(), ""));
        boolean isSuccess = adminUserDAO.save(user) > 0;
        if (isSuccess) {
            if (StringUtils.isNotBlank(user.getRoleIds())) {
                List<Long> ids = com.crop.seagulls.util.StringUtils.string2Long(user.getRoleIds(), ",");
                isSuccess = ReturnCode.isSuccess(roleService.addUserRoles(user.getId(), ids).getReturnCode());
            }
        }
        return isSuccess ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response findById(Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        User user = adminUserDAO.getById(id);
        user.setRoles(roleService.findByUserId(user.getId()));
        response.setResult(user);
        return response;
    }

    @Override
    public Paging<User> findList(User user) {
        List<User> users = adminUserDAO.getList(user);
        int total = adminUserDAO.getListCount(user);
        return new Paging<User>(total, user.getPage(), user.getPageSize(), users);
    }

    @Override
    public Response modify(User user) {
        user.setExceptId(user.getId());
        Paging<User> users = findList(user);
        if (CollectionUtils.isNotEmpty(users.getResult())) {
            return new Response(ReturnCode.USER_NAME_READY_REGISTER);
        }
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        user.setPassword(encoder.encodePassword(user.getPassword(), ""));
        Response response = adminUserDAO.update(user) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        return response;
    }

    @Override
    public Response remove(Long id) {
        return adminUserDAO.delete(id) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

}
