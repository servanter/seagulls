package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.UserIdentity;

@Repository
public interface IdentityDAO {

    List<UserIdentity> getList(UserIdentity userIdentity);

}
