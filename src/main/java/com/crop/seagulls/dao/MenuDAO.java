package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.admin.Menu;

@Repository
public interface MenuDAO {

    List<Menu> findByRoles(List<Long> roleIds);

    List<Menu> findAllMenusWithRoles();

    List<Menu> getList(Menu menu);

    int getListCount(Menu menu);

    int save(Menu menu);

    Menu getById(Long id);

    int update(Menu menu);

    int delete(Long id);

}
