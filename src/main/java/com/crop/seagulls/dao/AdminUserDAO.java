package com.crop.seagulls.dao;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.admin.User;

@Repository
public interface AdminUserDAO {

    User getByUser(User user);

}
