package com.crop.seagulls.service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.UserAuth;

public interface UserAuthService {

    public Response add(UserAuth userAuth);
    
    public Paging<UserAuth> findList(UserAuth userAuth);
    
    public UserAuth findByUserId(Long userId);
    
    public Response modify(UserAuth userAuth);
    
    public UserAuth findById(Long id);
}
