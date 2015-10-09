package com.crop.seagulls.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.admin.Role;

public interface RoleService {

    public List<Role> findByUserId(Long userId);

    public Paging<Role> findByRole(Role role);

    public Response findById(Long id);

    public Response modify(Role role);

    public Response add(Role role);

    public Response remove(Long id);
    
    public Response removeUserRole(Pair<Long, Long> userRolePair);

    public Response loadAllRolesAndWithCurRoles(Long userId);
    
    public Response addUserRoles(Long userId, List<Long> roleIds);

}
