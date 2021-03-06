package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.RoleDAO;
import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.entities.admin.Role;
import com.crop.seagulls.service.MenuService;
import com.crop.seagulls.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private MenuService menuService;

    @Override
    public List<Role> findByUserId(Long userId) {
        return roleDAO.getByUserId(userId);
    }

    @Override
    public Response add(Role role) {
        boolean isSuccess = roleDAO.save(role) > 0;
        if (isSuccess) {
            if (StringUtils.isNotBlank(role.getMenuIds())) {
                List<Long> ids = com.crop.seagulls.util.StringUtils.string2Long(role.getMenuIds(), ",");
                isSuccess = roleDAO.saveRoleMenus(role.getId(), ids) > 0;
            }
        }
        return isSuccess ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response findById(Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        Role role = roleDAO.getById(id);
        List<Long> ids = new ArrayList<Long>();
        ids.add(id);
        List<Menu> hasMenus = menuService.findByRoles(ids);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("role", role);
        map.put("hasMenus", hasMenus);
        response.setResult(map);
        return response;
    }

    @Override
    public Paging<Role> findByRole(Role role) {
        List<Role> roles = roleDAO.getByRole(role);
        int total = roleDAO.getByRoleCount(role);
        return new Paging<Role>(total, role.getPage(), role.getPageSize(), roles);
    }

    @Override
    public Response modify(Role role) {
        boolean isSuccess = roleDAO.update(role) > 0;
        if (isSuccess) {
            roleDAO.deleteRoleMenu(null, role.getId());
            List<Long> ids = com.crop.seagulls.util.StringUtils.string2Long(role.getMenuIds(), ",");
            if (CollectionUtils.isNotEmpty(ids)) {
                isSuccess = roleDAO.saveRoleMenus(role.getId(), ids) > 0;
            }
        }
        return isSuccess ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response remove(Long id) {
        boolean isSuccess = roleDAO.updateIsValid(id, false) > 0;
        if (isSuccess) {
            isSuccess = roleDAO.deleteUserRole(-1L, id) > 0;
            isSuccess = roleDAO.deleteRoleMenu(-1L, id) > 0;
        }
        return isSuccess ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response loadAllRolesAndWithCurRoles(Long userId) {
        Response response = new Response(ReturnCode.SUCCESS);
        Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
        List<Role> curRoles = findByUserId(userId);

        if (CollectionUtils.isNotEmpty(curRoles)) {
            List<Map<String, Object>> cur = new ArrayList<Map<String, Object>>();
            if (CollectionUtils.isNotEmpty(curRoles)) {
                for (Role r : curRoles) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", r.getId());
                    map.put("parentId", -1);
                    map.put("text", r.getRoleName());
                    cur.add(map);
                }
            }
            result.put("cur", cur);
        }

        Role role = new Role();
        role.setPageSize(-1);
        Paging<Role> rolePaging = findByRole(role);
        if (rolePaging != null && CollectionUtils.isNotEmpty(rolePaging.getResult())) {
            List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
            for (Role r : rolePaging.getResult()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", r.getId());
                map.put("parentId", -1);
                map.put("text", r.getRoleName());
                all.add(map);
            }
            result.put("all", all);
        }
        response.setResult(result);
        return response;
    }

    @Override
    public Response addUserRoles(Long userId, List<Long> roleIds) {
        return roleDAO.saveUserRoles(userId, roleIds) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response removeUserRole(Pair<Long, Long> userRolePair) {
        return roleDAO.deleteUserRole(userRolePair.getLeft(), userRolePair.getRight()) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

}
