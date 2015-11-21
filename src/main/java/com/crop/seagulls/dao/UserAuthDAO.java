package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.UserAuth;

@Repository
public interface UserAuthDAO {

    int save(UserAuth userAuth);

    List<UserAuth> getList(UserAuth userAuth);

    int getListCount(UserAuth userAuth);

    int update(UserAuth userAuth);

    UserAuth getById(Long id);

}
