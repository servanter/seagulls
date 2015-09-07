package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.admin.Role;

@Repository
public interface RoleDAO {

    List<Role> getByUserId(Long userId);

}
