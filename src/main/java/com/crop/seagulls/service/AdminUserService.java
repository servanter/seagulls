package com.crop.seagulls.service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.admin.User;

public interface AdminUserService {

    Response login(User user);

    public void refreshUserMenus(User user);
}
