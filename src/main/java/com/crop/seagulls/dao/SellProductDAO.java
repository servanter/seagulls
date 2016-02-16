package com.crop.seagulls.dao;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.SellProduct;

@Repository
public interface SellProductDAO {

    int save(SellProduct sellProduct);
    
    SellProduct getById(Long id);

}
