package com.crop.seagulls.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.admin.User;
import com.crop.seagulls.service.AdminUserService;
import com.crop.seagulls.service.CompanyService;

@Component
public class AdminUserCache {

    public static Map<Long, User> ALL_USER = new HashMap<Long, User>();

    @Autowired
    private AdminUserService adminUserService;

    @PostConstruct
    public void init() {
        List<User> users = adminUserService.findAll();
        Map<Long, User> map = new HashMap<Long, User>();
        if (CollectionUtils.isNotEmpty(users)) {
            for (User user : users) {
                map.put(user.getId(), user);
            }
        }
        ALL_USER = map;
    }

    public User getById(Long id) {
        return ALL_USER.get(id);
    }
}
