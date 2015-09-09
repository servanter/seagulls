package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.AdminUserDAO;
import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.entities.admin.User;
import com.crop.seagulls.service.AdminUserService;
import com.crop.seagulls.service.MenuService;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService, UserDetailsService {

    @Autowired
    private AdminUserDAO adminUserDAO;

    @Autowired
    private MenuService menuService;

    @Override
    public Response login(User user) {
        User u = adminUserDAO.getByUser(user);
        return u != null ? new Response(ReturnCode.SUCCESS, u) : new Response(ReturnCode.USER_OR_PASSWORD_UNVALID);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = adminUserDAO.getByUserName(username);
        if (StringUtils.isNotBlank(user.getRoleIds())) {
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
        return user;
    }

}
