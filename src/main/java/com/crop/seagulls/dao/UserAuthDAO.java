package com.crop.seagulls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.UserAuth;

@Repository
public interface UserAuthDAO {

    int save(UserAuth userAuth);

    List<UserAuth> getList(UserAuth userAuth);

    int getListCount(UserAuth userAuth);

    int update(UserAuth userAuth);
    
    int batchUpdate(@Param("auth") UserAuth userAuth, @Param("ids") List<Long> ids);

    UserAuth getById(Long id);

}
