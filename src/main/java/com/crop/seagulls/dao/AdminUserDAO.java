package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.admin.User;

@Repository
public interface AdminUserDAO {

    User getByUser(User user);
    
    User getByUserName(String userName);

    List<User> getList(User user);

    int getListCount(User user);

    User getById(Long id);

    int delete(Long id);

    int update(User user);

    int save(User user);

}
