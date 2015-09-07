package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.admin.Menu;

@Repository
public interface MenuDAO {

    List<Menu> findByRoles(List<Long> roleIds);

    List<Menu> findAllMenusWithRoles();

}
