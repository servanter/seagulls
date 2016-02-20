package com.crop.seagulls.dao;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Third;

@Repository
public interface ThirdDAO {

    int save(Third third);

    Third getByThird(Third third);
    
    Third getByThirdUnionId(Third third);

}
