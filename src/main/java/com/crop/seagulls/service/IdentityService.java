package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.entities.UserIdentity;

public interface IdentityService {

    public List<UserIdentity> findList(UserIdentity userIdentity);
}
