package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
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

    @Override
    public Response loadMenusAndWithCurMenus(String roleIds) {
        Map<String, List<Menu>> result = new HashMap<String, List<Menu>>();
        List<Menu> allMenus = findAll();
        if (StringUtils.isNotBlank(roleIds)) {
            List<Long> ids = new ArrayList<Long>();
            for (String id : roleIds.split(",")) {
                ids.add(Long.parseLong(id));
            }
            List<Menu> curMenus = findByRoles(ids);
            result.put("curMenus", curMenus);
        }
        result.put("allMenus", allMenus);
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        response.setResult(result);
        return response;
    }

    @Override
    public Paging<Menu> findList(Menu menu) {
        List<Menu> menus = menuDAO.getList(menu);
        int total = menuDAO.getListCount(menu);
        return new Paging<Menu>(total, menu.getPage(), menu.getPageSize(), menus);
    }

    @Override
    public Response add(Menu menu) {
        Paging<Menu> menus = findList(menu);
        if (CollectionUtils.isNotEmpty(menus.getResult())) {
            return new Response(ReturnCode.MENU_NAME_ALREADY_UNVALID);
        }
        return menuDAO.save(menu) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response findById(Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        Menu menu = menuDAO.getById(id);
        response.setResult(menu);
        return response;
    }

    @Override
    public Response modify(Menu menu) {
        Paging<Menu> menus = findList(menu);
        if (CollectionUtils.isNotEmpty(menus.getResult())) {
            return new Response(ReturnCode.MENU_NAME_ALREADY_UNVALID);
        }
        return menuDAO.update(menu) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response remove(Long id) {
        return menuDAO.delete(id) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

}
