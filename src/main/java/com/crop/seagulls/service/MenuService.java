package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.admin.Menu;

public interface MenuService {

    public List<Menu> findByRoles(List<Long> roleIds);

    public List<Menu> findAll();

    public Response loadMenusAndWithCurMenus(String roleIds);

    public Paging<Menu> findList(Menu menu);

    public Response findById(Long id);

    public Response modify(Menu menu);

    public Response add(Menu menu);

    public Response remove(Long id);

}
