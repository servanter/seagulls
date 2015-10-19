package com.crop.seagulls.service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.admin.User;

public interface AdminUserService {

    public Response login(User user);

    public void refreshUserMenus(User user);

    public Paging<User> findList(User user);

    public Response findById(Long id);

    public Response modify(User user);

    public Response add(User user);

    public Response remove(Long id);
}