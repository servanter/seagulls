package com.crop.seagulls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.admin.Role;

@Repository
public interface RoleDAO {

    List<Role> getByUserId(Long userId);

    int save(Role role);

    Role getById(Long id);

    int update(Role role);

    int updateIsValid(@Param("id") Long id, @Param("isValid") Boolean isValid);

    int deleteUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int deleteRoleMenu(@Param("menuId") Long menuId, @Param("roleId") Long roleId);
    
    List<Role> getByRole(Role role);

    int getByRoleCount(Role role);
    
    int saveRoleMenus(@Param("roleId") Long roleId, @Param("menuIds") List<Long> menuIds);

}
