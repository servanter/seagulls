package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
